package one.block.recenteosblocks.data.datasources

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.eos.BlockByIdQuery
import com.eos.LatestBlockQuery
import com.google.gson.JsonObject
import one.block.recenteosblocks.data.datasources.mappers.BlockGraphQlMapper
import one.block.recenteosblocks.data.db.AppDatabase
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.db.entities.BlockchainInfo
import one.block.recenteosblocks.data.network.SafeApiRequest
import one.block.recenteosblocks.util.Constants.BLOCK_NUM_OR_ID

class BlockGraphQlDataSource(
    private val apolloClient: ApolloClient,
    private val blockGraphQlMapper: BlockGraphQlMapper,
    private val db: AppDatabase
) : SafeApiRequest(), BlockApiDataSource {

    override suspend fun getBlockchainInfo(): BlockchainInfo? {
        return graphQlRequest {
            apolloClient.query(LatestBlockQuery()).toDeferred().await()
        }?.latestBlock?.let {
            blockGraphQlMapper.transform(it)
        }
    }

    override suspend fun getBlock(blockNumber: JsonObject): Block? {
        return graphQlRequest {
            apolloClient.query(BlockByIdQuery(blockNumber.get(BLOCK_NUM_OR_ID).asString)).toDeferred().await()
        }?.blockById?.let {
            blockGraphQlMapper.transform(it)
        }
    }

    override suspend fun saveBlock(block: Block) = db.getBlockDao().insertOrUpdate(block)
}
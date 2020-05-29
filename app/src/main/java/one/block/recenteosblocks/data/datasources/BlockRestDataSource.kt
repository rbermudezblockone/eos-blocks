package one.block.recenteosblocks.data.datasources

import com.google.gson.JsonObject
import one.block.recenteosblocks.data.db.AppDatabase
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.network.SafeApiRequest

class BlockRestDataSource(
    private val eosApi: EosAPI,
    private val db: AppDatabase
) : SafeApiRequest(), BlockApiDataSource {

    override suspend fun getBlockchainInfo() = apiRequest {
        eosApi.getBlockchainInfo()
    }

    override suspend fun getBlock(blockNumber: JsonObject) = apiRequest {
        eosApi.getBlock(blockNumber)
    }

    override suspend fun saveBlock(block: Block) = db.getBlockDao().insertOrUpdate(block)
}
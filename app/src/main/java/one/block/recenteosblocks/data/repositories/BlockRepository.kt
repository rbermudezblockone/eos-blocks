package one.block.recenteosblocks.data.repositories

import com.google.gson.JsonObject
import one.block.recenteosblocks.data.datasources.BlockApiDataSource
import one.block.recenteosblocks.data.db.AppDatabase
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.network.SafeApiRequest

class BlockRepository(
    private val blockApiDataSource: BlockApiDataSource
) : SafeApiRequest() {

    suspend fun getBlockchainInfo() = blockApiDataSource.getBlockchainInfo()

    suspend fun getBlock(blockNumber: JsonObject) = blockApiDataSource.getBlock(blockNumber)

    suspend fun saveBlock(block: Block) = blockApiDataSource.saveBlock(block)
}
package one.block.recenteosblocks.data.datasources

import com.google.gson.JsonObject
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.db.entities.BlockchainInfo

interface BlockApiDataSource {
    suspend fun getBlockchainInfo(): BlockchainInfo?
    suspend fun getBlock(blockNumber: JsonObject): Block?
    suspend fun saveBlock(block: Block): Long
}
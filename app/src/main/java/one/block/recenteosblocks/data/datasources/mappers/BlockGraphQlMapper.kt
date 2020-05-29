package one.block.recenteosblocks.data.datasources.mappers

import com.eos.BlockByIdQuery
import com.eos.LatestBlockQuery
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.db.entities.BlockchainInfo

class BlockGraphQlMapper {

    fun transform(input: LatestBlockQuery.LatestBlock): BlockchainInfo {
        return BlockchainInfo(
            headBlockNum = input.id
        )
    }

    fun transform(input: BlockByIdQuery.BlockById): Block {
        return Block(
            id = input.id,
            blockNum = input.block_num.toString(),
            timestamp = input.timestamp,
            producer = input.producer,
            confirmed = "",
            previous = input.previous,
            transactionMroot = "",
            actionMroot = "",
            scheduleVersion = "",
            refBlockPrefix = ""
        )
    }
}
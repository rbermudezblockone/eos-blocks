package one.block.recenteosblocks.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_block_detail.block_action_mroot
import kotlinx.android.synthetic.main.activity_block_detail.block_confirmed
import kotlinx.android.synthetic.main.activity_block_detail.block_id
import kotlinx.android.synthetic.main.activity_block_detail.block_number
import kotlinx.android.synthetic.main.activity_block_detail.block_previous
import kotlinx.android.synthetic.main.activity_block_detail.block_producer
import kotlinx.android.synthetic.main.activity_block_detail.block_ref_block_prefix
import kotlinx.android.synthetic.main.activity_block_detail.block_schedule_version
import kotlinx.android.synthetic.main.activity_block_detail.block_timestamp
import kotlinx.android.synthetic.main.activity_block_detail.block_transaction_mroot
import one.block.recenteosblocks.R
import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.repositories.BlockRepository
import one.block.recenteosblocks.databinding.ActivityBlockDetailBinding
import one.block.recenteosblocks.util.Constants.BLOCK_KEY

class BlockDetailActivity : AppCompatActivity() {

    private lateinit var factory: BlockDetailViewModelFactory
    private lateinit var viewModel: BlockDetailViewModel

    companion object {
        fun getIntent(context: Context, block: Block): Intent {
            return Intent(context, BlockDetailActivity::class.java).putExtra(BLOCK_KEY, block)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObservers()
        getBlockDetail()
    }

    private fun initViewModel() {
        val binding: ActivityBlockDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_block_detail)
        val eosAPI = EosAPI()
        val blockRepository = BlockRepository(eosAPI)
        factory = BlockDetailViewModelFactory(blockRepository)
        viewModel = ViewModelProvider(this@BlockDetailActivity, factory).get(BlockDetailViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initObservers() {
        viewModel.block.observe(this, Observer {
            it.apply {
                block_id.text = id
                block_number.text = blockNum
                block_producer.text = producer
                block_timestamp.text = timestamp
                block_confirmed.text = confirmed
                block_previous.text = previous
                block_transaction_mroot.text = transactionMroot
                block_action_mroot.text = actionMroot
                block_schedule_version.text = scheduleVersion
                block_ref_block_prefix.text = refBlockPrefix
            }
        })
    }

    private fun getBlockDetail() {
        val block: Block? = intent.extras?.getParcelable(BLOCK_KEY)
        block?.let {
            viewModel.getBlockDetail(block)
        }
    }
}

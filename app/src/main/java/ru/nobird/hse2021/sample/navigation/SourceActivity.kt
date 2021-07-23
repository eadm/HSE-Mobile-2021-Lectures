package ru.nobird.hse2021.sample.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ActivitySourceBinding
import ru.nobird.hse2021.sample.extension.snackbar
import ru.nobird.hse2021.sample.navigation.adapter.ItemsListAdapter
import ru.nobird.hse2021.sample.navigation.contract.IncrementActivityContract
import ru.nobird.hse2021.sample.navigation.model.Action
import ru.nobird.hse2021.sample.navigation.viewmodel.IncrementViewModel

class SourceActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySourceBinding

    private lateinit var itemsAdapter: ItemsListAdapter

    private val viewModel by viewModels<IncrementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        itemsAdapter = ItemsListAdapter()

        with(viewBinding.sampleRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewBinding.sampleButtom.setOnClickListener {
            viewModel.onAddNewValue()
        }

        val contract =
            registerForActivityResult(IncrementActivityContract(), viewModel::onChangeDelta)

        viewBinding.secondButtom.setOnClickListener {
            contract.launch(null)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { itemsList ->
                    itemsAdapter.submitList(itemsList)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.actions.collect { action ->
                    when (action) {
                        is Action.ShowMessage ->
                            showMessage(action.message)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countDelta.collect { countDelta ->
                    viewBinding.sampleButtom.text = getString(R.string.button_text, countDelta)
                }
            }
        }
    }

    private fun showMessage(message: String) {
        viewBinding.root.snackbar(message)
    }
}
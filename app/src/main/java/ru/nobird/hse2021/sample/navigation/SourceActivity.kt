package ru.nobird.hse2021.sample.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ActivitySourceBinding
import ru.nobird.hse2021.sample.extension.snackbar
import ru.nobird.hse2021.sample.navigation.adapter.ItemsListAdapter
import ru.nobird.hse2021.sample.navigation.model.Item

class SourceActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySourceBinding

    private lateinit var itemsAdapter: ItemsListAdapter
    private var itemsList: List<Item> = emptyList()

    private var counter = 0
    private var countDelta = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        counter = savedInstanceState?.getInt(KEY_COUNTER, 0) ?: 0
        countDelta = savedInstanceState?.getInt(KEY_DELTA, 1) ?: 1

        itemsList = savedInstanceState?.getParcelableArrayList(KEY_LIST)
            ?: listOf(Item.Header("Header"), Item.WithText("Sample text"))

        itemsAdapter = ItemsListAdapter()
        itemsAdapter.submitList(itemsList)

        with(viewBinding.sampleRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewBinding.sampleButtom.text = getString(R.string.button_text, countDelta)
        viewBinding.sampleButtom.setOnClickListener {
            counter += countDelta
            itemsList = itemsList + Item.WithText(counter.toString())
            itemsAdapter.submitList(itemsList)
        }

        viewBinding.secondButtom.setOnClickListener {
            showSecondActivity()
        }
    }

    private fun showSecondActivity() {
        startActivityForResult(Intent(this, SecondActivity::class.java), REQUEST_CODE)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counter)
        outState.putInt(KEY_DELTA, countDelta)
        outState.putParcelableArrayList(KEY_LIST, ArrayList(itemsList))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            countDelta = data.getIntExtra(SecondActivity.KEY_DELTA, countDelta)
            viewBinding.sampleButtom.text = getString(R.string.button_text, countDelta)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showMessage(message: String) {
        viewBinding.root.snackbar(message)
    }

    companion object {
        private const val KEY_COUNTER = "counter"
        private const val KEY_LIST = "list"
        private const val KEY_DELTA = "delta"

        private const val REQUEST_CODE = 3876
    }
}
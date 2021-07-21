package ru.nobird.hse2021.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nobird.hse2021.sample.adapter.ItemsAdapter
import ru.nobird.hse2021.sample.adapter.ItemsListAdapter
import ru.nobird.hse2021.sample.databinding.ActivityMainBinding
import ru.nobird.hse2021.sample.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var counter = 0
    private lateinit var itemsAdapter: ItemsListAdapter
    private var itemsList: List<Item> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        counter = savedInstanceState?.getInt(KEY_COUNTER, 0) ?: 0

        itemsList = savedInstanceState?.getParcelableArrayList(KEY_LIST)
            ?: listOf(Item.Header("Header"), Item.WithText("Sample text"))

        itemsAdapter = ItemsListAdapter()
        itemsAdapter.submitList(itemsList)

        with(viewBinding.sampleRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewBinding.sampleButtom.setOnClickListener {
            counter++
            itemsList = itemsList + Item.WithText(counter.toString())
            itemsAdapter.submitList(itemsList)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counter)
        outState.putParcelableArrayList(KEY_LIST, ArrayList(itemsList))
    }

    companion object {
        private const val KEY_COUNTER = "counter"
        private const val KEY_LIST = "counter"
    }
}
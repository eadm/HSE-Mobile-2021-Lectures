package ru.nobird.hse2021.sample.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nobird.hse2021.sample.databinding.ActivitySecondBinding
import ru.nobird.hse2021.sample.navigation.adapter.IncrementAdapter

class SecondActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        with(viewBinding.recycler) {
            layoutManager = LinearLayoutManager(this@SecondActivity)

            val itemsDelta = listOf(1, 2, 3)
            adapter =
                IncrementAdapter {
                    setResult(RESULT_OK, Intent().putExtra(KEY_DELTA, itemsDelta[it]))
                    finish()
                }.apply { items = itemsDelta }
        }
    }

    companion object {
        const val KEY_DELTA = "delta"
    }
}
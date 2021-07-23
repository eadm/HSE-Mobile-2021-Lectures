package ru.nobird.hse2021.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nobird.hse2021.sample.databinding.ActivityMainBinding
import ru.nobird.hse2021.sample.navigation.SourceActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.navigationTopic.setOnClickListener {
            startActivity(Intent(this, SourceActivity::class.java))
        }
    }
}
package ru.nobird.hse2021.sample.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ActivityFragmentsBinding
import ru.nobird.hse2021.sample.fragment.arguments.ArgumentsActivity
import ru.nobird.hse2021.sample.fragment.dynamic.FragmentDynamicActivity
import ru.nobird.hse2021.sample.fragment.multiple.MultipleFragmentsActivity
import ru.nobird.hse2021.sample.fragment.navigation.NavigationActivity

class FragmentsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.apply {
            title = getString(R.string.topic_fragment)
            setDisplayHomeAsUpEnabled(true)
        }

        viewBinding.dynamicTopic
            .setOnClickListener { startActivity<FragmentDynamicActivity>() }

        viewBinding.multipleTopic
            .setOnClickListener { startActivity<MultipleFragmentsActivity>() }

        viewBinding.argumentsTopic
            .setOnClickListener { startActivity<ArgumentsActivity>() }

        viewBinding.navigationTopic
            .setOnClickListener { startActivity<NavigationActivity>() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    companion object {
        private inline fun <reified T : Activity> Context.startActivity() {
            startActivity(Intent(this, T::class.java))
        }
    }
}
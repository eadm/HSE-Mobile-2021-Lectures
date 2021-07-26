package ru.nobird.hse2021.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nobird.hse2021.sample.databinding.ActivityMainBinding
import ru.nobird.hse2021.sample.dialogfragment.DialogsActivity
import ru.nobird.hse2021.sample.extension.startActivity
import ru.nobird.hse2021.sample.fragment.FragmentsActivity
import ru.nobird.hse2021.sample.githubuserlist.presentation.ui.activity.GithubUserListActivity
import ru.nobird.hse2021.sample.navigation.SourceActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.navigationTopic.setOnClickListener {
            startActivity<SourceActivity>()
        }
        viewBinding.fragmentTopic.setOnClickListener {
            startActivity<FragmentsActivity>()
        }
        viewBinding.dialogFragmentTopic.setOnClickListener {
            startActivity<DialogsActivity>()
        }
        viewBinding.githubTopic.setOnClickListener {
            startActivity<GithubUserListActivity>()
        }
    }
}
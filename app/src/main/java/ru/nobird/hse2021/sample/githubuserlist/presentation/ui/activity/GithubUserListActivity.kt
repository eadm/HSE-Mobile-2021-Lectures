package ru.nobird.hse2021.sample.githubuserlist.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.nobird.hse2021.sample.databinding.ActivityGithubUsersBinding
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel
import ru.nobird.hse2021.sample.githubuserlist.presentation.injection.ViewModelFactory

class GithubUserListActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityGithubUsersBinding

    private val viewModel by viewModels<GithubUsersViewModel> { ViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGithubUsersBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}
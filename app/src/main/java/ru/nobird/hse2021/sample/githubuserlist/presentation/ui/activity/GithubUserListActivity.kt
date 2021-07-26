package ru.nobird.hse2021.sample.githubuserlist.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.nobird.hse2021.sample.databinding.ActivityGithubUsersBinding
import ru.nobird.hse2021.sample.extension.setOnPaginationListener
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel
import ru.nobird.hse2021.sample.githubuserlist.presentation.injection.ViewModelFactory
import ru.nobird.hse2021.sample.githubuserlist.presentation.model.GithubUsersState
import ru.nobird.hse2021.sample.githubuserlist.presentation.ui.adapter.UserListAdapter

class GithubUserListActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityGithubUsersBinding

    private val viewModel by viewModels<GithubUsersViewModel> { ViewModelFactory(applicationContext) }
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGithubUsersBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        userListAdapter = UserListAdapter()
        viewBinding.stateData.adapter = userListAdapter

        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { render(it) }
        }

        lifecycleScope.launch {
            viewModel.actions
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {  }
        }

        viewBinding.stateData.setOnPaginationListener {
            viewModel.fetchNextPage()
        }
        viewBinding.stateErrorTryAgain.setOnClickListener {
            viewModel.fetchData(forceUpdate = true)
        }
        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchData(forceUpdate = true)
            viewBinding.swipeRefresh.isRefreshing = false
        }

        viewModel.fetchData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    private fun render(state: GithubUsersState) {
        viewBinding.stateLoading.isVisible =
            state is GithubUsersState.Idle || state is GithubUsersState.Loading

        viewBinding.stateError.isVisible =
            state is GithubUsersState.NetworkError

        viewBinding.stateData.isVisible =
            state is GithubUsersState.Data

        if (state is GithubUsersState.Data) {
            userListAdapter.submitList(state.pagedData.data)
        }
    }
}
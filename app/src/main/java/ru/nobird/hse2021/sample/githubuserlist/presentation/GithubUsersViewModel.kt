package ru.nobird.hse2021.sample.githubuserlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nobird.hse2021.sample.githubuserlist.data.GithubUsersRepository
import ru.nobird.hse2021.sample.githubuserlist.presentation.model.GithubUsersAction
import ru.nobird.hse2021.sample.githubuserlist.presentation.model.GithubUsersState

class GithubUsersViewModel(
    private val repository: GithubUsersRepository
) : ViewModel() {
    private val _state = MutableStateFlow<GithubUsersState>(GithubUsersState.Idle)
    val state: Flow<GithubUsersState>
        get() = _state

    private val _actions = Channel<GithubUsersAction>()
    val actions: Flow<GithubUsersAction> = _actions.receiveAsFlow()

    private var paginationJob: Job? = null

    fun fetchData(forceUpdate: Boolean = false) {
        paginationJob?.cancel()
    }

    fun fetchNextPage() {
        paginationJob = viewModelScope.launch {

        }
    }
}
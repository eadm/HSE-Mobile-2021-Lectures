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
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
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
        if (_state.value !is GithubUsersState.Idle &&
            _state.value !is GithubUsersState.NetworkError &&
            !(forceUpdate && _state.value is GithubUsersState.Data)) {
            return
        }

        _state.value = GithubUsersState.Loading
        paginationJob?.cancel()
        viewModelScope.launch {
            _state.value =
                try {
                    val users = repository.getUsers("abc", 1)
                    GithubUsersState.Data(pagedData = users)
                } catch (e: Exception) {
                    GithubUsersState.NetworkError
                }
        }
    }

    fun fetchNextPage() {
        val oldState = _state.value as? GithubUsersState.Data
            ?: return

        if (!oldState.pagedData.hasNext) {
            return
        }

        _state.value = oldState.copy(isPaginationLoading = true)

        paginationJob = viewModelScope.launch {
            try {
                val users = repository.getUsers("abc", oldState.pagedData.page + 1)
                _state.value = oldState.copy(pagedData = oldState.pagedData.merge(users, List<GithubUser>::plus))
            } catch (e: Exception) {
                _state.value = oldState.copy(isPaginationLoading = false)
                _actions.send(GithubUsersAction.ShowNetworkError)
            }
        }
    }
}
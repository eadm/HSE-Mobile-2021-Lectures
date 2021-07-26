package ru.nobird.hse2021.sample.githubuserlist.presentation.model

import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
import ru.nobird.hse2021.sample.githubuserlist.domain.model.PagedData

sealed interface GithubUsersState {
    object Idle : GithubUsersState
    object Loading : GithubUsersState
    object NetworkError : GithubUsersState
    data class Data(
        val pagedData: PagedData<List<GithubUser>>,
        val isPaginationLoading: Boolean
    ) : GithubUsersState
}
package ru.nobird.hse2021.sample.githubuserlist.presentation.model

sealed interface GithubUsersState {
    object Idle : GithubUsersState
}
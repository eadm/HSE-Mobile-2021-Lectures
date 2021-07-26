package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass == GithubUsersViewModel::class.java) {
            GithubUsersViewModelFactory.create() as T
        } else {
            throw IllegalArgumentException("Unsupported view model of class = $modelClass")
        }
}
package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel

class ViewModelFactory(
    applicationContext: Context
) : ViewModelProvider.Factory {
    private val githubUsersViewModelFactory =
        GithubUsersViewModelFactory(applicationContext)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass == GithubUsersViewModel::class.java) {
            githubUsersViewModelFactory.create() as T
        } else {
            throw IllegalArgumentException("Unsupported view model of class = $modelClass")
        }
}
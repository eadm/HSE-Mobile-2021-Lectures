package ru.nobird.hse2021.sample.githubuserlist.view.injection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel
import ru.nobird.hse2021.sample.githubuserlist.view.injection.base.ViewModelKey

@Module
interface GithubUsersPresentationModule {
    @Binds
    @ViewModelKey(GithubUsersViewModel::class)
    @IntoMap
    fun bindViewModel(githubUsersViewModel: GithubUsersViewModel): ViewModel
}
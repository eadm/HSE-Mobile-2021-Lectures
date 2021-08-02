package ru.nobird.hse2021.sample.githubuserlist.view.injection

import dagger.Subcomponent
import ru.nobird.hse2021.sample.githubuserlist.presentation.ui.activity.GithubUserListActivity

@Subcomponent(modules = [
    GithubUsersDataModule::class,
    GithubUsersPresentationModule::class
])
interface GithubUserListComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): GithubUserListComponent
    }

    fun inject(githubUserListActivity: GithubUserListActivity)
}
package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import android.app.Application
import android.content.Context
import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel
import ru.nobird.hse2021.sample.githubuserlist.view.injection.GithubUsersDataModule
import ru.nobird.hse2021.sample.githubuserlist.view.injection.NetworkModule

class GithubUsersViewModelFactory(
    private val applicationContext: Context
) {
    fun create(): GithubUsersViewModel {
        val json = NetworkModule.provideJson()
        val retrofit = NetworkModule.provideRetrofit(json)

        // remote
        val service = GithubUsersDataModule
            .provideService(retrofit)

        val appDispatchers = GithubUsersDataModule
            .provideAppDispatchers()

        val remoteDataSource = GithubUsersDataModule
            .provideRemoteDataSource(appDispatchers, service)

        // cache
        val appDatabase = GithubUsersDataModule
            .provideAppDatabase(applicationContext as Application)

        val dao = GithubUsersDataModule.provideDao(appDatabase)

        val mapper = GithubUsersDataModule.provideUserEntityMapper()

        val cacheDataSource = GithubUsersDataModule
            .provideCacheDataSource(dao, mapper, appDispatchers)

        // repository
        val repository = GithubUsersDataModule
            .provideRepository(remoteDataSource, cacheDataSource)

        return GithubUsersViewModel(repository)
    }
}
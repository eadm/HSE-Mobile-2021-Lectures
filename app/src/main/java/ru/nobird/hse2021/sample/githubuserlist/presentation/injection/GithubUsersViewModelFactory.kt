package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import ru.nobird.hse2021.sample.githubuserlist.presentation.GithubUsersViewModel

object GithubUsersViewModelFactory {
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

        val cacheDataSource = GithubUsersDataModule
            .provideCacheDataSource()

        val repository = GithubUsersDataModule
            .provideRepository(remoteDataSource, cacheDataSource)

        return GithubUsersViewModel(repository)
    }
}
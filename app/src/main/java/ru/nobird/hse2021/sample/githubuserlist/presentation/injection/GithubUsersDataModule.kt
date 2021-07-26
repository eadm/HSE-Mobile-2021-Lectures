package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.create
import ru.nobird.hse2021.sample.githubuserlist.data.GithubUsersRepository
import ru.nobird.hse2021.sample.githubuserlist.data.cache.GithubCacheDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.GithubRemoteDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.service.GithubUsersService
import ru.nobird.hse2021.sample.githubuserlist.domain.base.AppDispatchers

object GithubUsersDataModule {
    fun provideService(retrofit: Retrofit): GithubUsersService =
        retrofit.create()

    fun provideRemoteDataSource(
        appDispatchers: AppDispatchers,
        service: GithubUsersService
    ): GithubRemoteDataSource =
        GithubRemoteDataSource(appDispatchers, service)

    fun provideCacheDataSource(

    ): GithubCacheDataSource =
        GithubCacheDataSource()

    fun provideRepository(
        remoteDataSource: GithubRemoteDataSource,
        cacheDataSource: GithubCacheDataSource
    ): GithubUsersRepository =
        GithubUsersRepository(remoteDataSource, cacheDataSource)

    fun provideAppDispatchers(): AppDispatchers =
        object : AppDispatchers {
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO

            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
        }
}
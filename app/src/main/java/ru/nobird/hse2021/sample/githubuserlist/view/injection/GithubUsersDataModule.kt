package ru.nobird.hse2021.sample.githubuserlist.view.injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.create
import ru.nobird.hse2021.sample.githubuserlist.data.GithubUsersRepository
import ru.nobird.hse2021.sample.githubuserlist.data.cache.AppDatabase
import ru.nobird.hse2021.sample.githubuserlist.data.cache.GithubCacheDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.cache.dao.GithubUsersDao
import ru.nobird.hse2021.sample.githubuserlist.data.cache.mapper.GithubUserEntityMapper
import ru.nobird.hse2021.sample.githubuserlist.data.remote.GithubRemoteDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.service.GithubUsersService
import ru.nobird.hse2021.sample.githubuserlist.domain.base.AppDispatchers
import javax.inject.Singleton

@Module
object GithubUsersDataModule {
    // remote
    @Provides
    fun provideService(retrofit: Retrofit): GithubUsersService =
        retrofit.create()

    @Provides
    fun provideRemoteDataSource(
        appDispatchers: AppDispatchers,
        service: GithubUsersService
    ): GithubRemoteDataSource =
        GithubRemoteDataSource(appDispatchers, service)


    // cache
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "github")
            .build()

    @Provides
    fun provideDao(appDatabase: AppDatabase): GithubUsersDao =
        appDatabase.userDao()

    @Provides
    fun provideUserEntityMapper(): GithubUserEntityMapper =
        GithubUserEntityMapper()

    @Provides
    fun provideCacheDataSource(
        dao: GithubUsersDao,
        githubUserEntityMapper: GithubUserEntityMapper,
        appDispatchers: AppDispatchers
    ): GithubCacheDataSource =
        GithubCacheDataSource(dao, githubUserEntityMapper, appDispatchers)

    @Provides
    fun provideRepository(
        remoteDataSource: GithubRemoteDataSource,
        cacheDataSource: GithubCacheDataSource
    ): GithubUsersRepository =
        GithubUsersRepository(remoteDataSource, cacheDataSource)

    @Provides
    fun provideAppDispatchers(): AppDispatchers =
        object : AppDispatchers {
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO

            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
        }
}
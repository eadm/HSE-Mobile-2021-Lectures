package ru.nobird.hse2021.sample.githubuserlist.data

import ru.nobird.hse2021.sample.githubuserlist.data.cache.GithubCacheDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.GithubRemoteDataSource

class GithubUsersRepository(
    private val remoteDataSource: GithubRemoteDataSource,
    private val cacheDataSource: GithubCacheDataSource
) {

}
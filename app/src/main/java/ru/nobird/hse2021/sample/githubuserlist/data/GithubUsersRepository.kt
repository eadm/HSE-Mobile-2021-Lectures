package ru.nobird.hse2021.sample.githubuserlist.data

import ru.nobird.hse2021.sample.githubuserlist.data.cache.GithubCacheDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.GithubRemoteDataSource
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
import ru.nobird.hse2021.sample.githubuserlist.domain.model.PagedData
import java.io.IOException

class GithubUsersRepository(
    private val remoteDataSource: GithubRemoteDataSource,
    private val cacheDataSource: GithubCacheDataSource
) {
    suspend fun getUsers(query: String, page: Int): PagedData<List<GithubUser>> =
        try {
            val remoteUsers =
                remoteDataSource.getUsers(query, page, PER_PAGE)
            cacheDataSource.replaceUsers(remoteUsers.data)
            remoteUsers
        } catch (e: IOException) {
            PagedData(data = cacheDataSource.getUsers())
        }

    companion object {
        private const val PER_PAGE = 10
    }
}
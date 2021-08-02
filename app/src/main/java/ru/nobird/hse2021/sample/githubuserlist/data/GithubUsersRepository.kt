package ru.nobird.hse2021.sample.githubuserlist.data

import ru.nobird.hse2021.sample.githubuserlist.data.cache.GithubCacheDataSource
import ru.nobird.hse2021.sample.githubuserlist.data.remote.GithubRemoteDataSource
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
import ru.nobird.hse2021.sample.githubuserlist.domain.model.PagedData
import java.io.IOException
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource,
    private val cacheDataSource: GithubCacheDataSource
) {
    @Synchronized
    suspend fun getUsers(query: String, page: Int): PagedData<List<GithubUser>> =
        try {
            val remoteUsers =
                remoteDataSource.getUsers(query, page, PER_PAGE)
            if (page == 1) {
                cacheDataSource.replaceUsers(remoteUsers.data)
            }
            remoteUsers
        } catch (e: IOException) {
            val cachedUsers = cacheDataSource.getUsers()
            if (cachedUsers.isEmpty()) {
                throw e
            }
            PagedData(data = cacheDataSource.getUsers())
        }

    companion object {
        private const val PER_PAGE = 10
    }
}
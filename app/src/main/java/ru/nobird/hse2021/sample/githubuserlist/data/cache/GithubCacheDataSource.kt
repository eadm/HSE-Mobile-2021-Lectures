package ru.nobird.hse2021.sample.githubuserlist.data.cache

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.nobird.hse2021.sample.githubuserlist.data.cache.dao.GithubUsersDao
import ru.nobird.hse2021.sample.githubuserlist.data.cache.mapper.GithubUserEntityMapper
import ru.nobird.hse2021.sample.githubuserlist.domain.base.AppDispatchers
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
import javax.inject.Inject

class GithubCacheDataSource @Inject constructor(
    private val dao: GithubUsersDao,
    private val githubUserEntityMapper: GithubUserEntityMapper,
    private val appDispatchers: AppDispatchers
) {
    private val mutex = Mutex()

    suspend fun getUsers(): List<GithubUser> =
        withContext(appDispatchers.io) {
            mutex.withLock {
                dao.getUsers().map(githubUserEntityMapper::fromEntity)
            }
        }

    suspend fun replaceUsers(users: List<GithubUser>) {
        withContext(appDispatchers.io) {
            mutex.withLock {
                dao.deleteAll()
                dao.insertAll(users.map(githubUserEntityMapper::toEntity))
            }
        }
    }
}
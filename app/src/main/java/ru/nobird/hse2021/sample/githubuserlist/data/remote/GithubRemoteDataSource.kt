package ru.nobird.hse2021.sample.githubuserlist.data.remote

import kotlinx.coroutines.withContext
import ru.nobird.hse2021.sample.githubuserlist.data.remote.service.GithubUsersService
import ru.nobird.hse2021.sample.githubuserlist.domain.base.AppDispatchers
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser
import ru.nobird.hse2021.sample.githubuserlist.domain.model.PagedData
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val githubUsersService: GithubUsersService
) {
    suspend fun getUsers(query: String, page: Int, perPage: Int): PagedData<List<GithubUser>> =
        withContext(dispatchers.io) {
            val response = githubUsersService.getUsers(query, page, perPage)
            PagedData(
                page = page,
                hasNext = page * perPage < response.totalCount,
                hasPrev = false,
                data = response.items
            )
        }
}
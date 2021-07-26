package ru.nobird.hse2021.sample.githubuserlist.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.nobird.hse2021.sample.githubuserlist.data.remote.model.GithubUsersResponse

interface GithubUsersService {
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubUsersResponse
}
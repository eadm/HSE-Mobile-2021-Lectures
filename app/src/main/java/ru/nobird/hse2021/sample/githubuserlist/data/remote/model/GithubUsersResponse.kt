package ru.nobird.hse2021.sample.githubuserlist.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser

@Serializable
data class GithubUsersResponse(
    @SerialName("total_count")
    val totalCount: Long,

    @SerialName("incomplete_results")
    val incompleteResults: Boolean,

    @SerialName("items")
    val items: List<GithubUser>
)
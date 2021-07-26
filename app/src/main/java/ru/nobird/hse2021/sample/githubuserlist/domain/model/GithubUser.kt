package ru.nobird.hse2021.sample.githubuserlist.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    @SerialName("id")
    val id: Long,

    @SerialName("avatar_url")
    val avatarUrl: String,

    @SerialName("login")
    val login: String,

    @SerialName("html_url")
    val homeUrl: String
)
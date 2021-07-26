package ru.nobird.hse2021.sample.githubuserlist.data.cache.mapper

import ru.nobird.hse2021.sample.githubuserlist.data.cache.model.GithubUsersEntity
import ru.nobird.hse2021.sample.githubuserlist.domain.model.GithubUser

class GithubUserEntityMapper {
    fun toEntity(user: GithubUser): GithubUsersEntity =
        GithubUsersEntity(
            user.id,
            user.avatarUrl,
            user.login,
            user.homeUrl
        )

    fun fromEntity(user: GithubUsersEntity): GithubUser =
        GithubUser(
            user.id,
            user.avatarUrl,
            user.login,
            user.homeUrl
        )
}
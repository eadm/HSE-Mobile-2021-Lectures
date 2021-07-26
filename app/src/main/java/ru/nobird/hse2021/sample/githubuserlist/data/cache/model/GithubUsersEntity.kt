package ru.nobird.hse2021.sample.githubuserlist.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class GithubUsersEntity(
    @PrimaryKey val id: Long,
    val avatarUrl: String,
    val login: String,
    val homeUrl: String
)
package ru.nobird.hse2021.sample.githubuserlist.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nobird.hse2021.sample.githubuserlist.data.cache.dao.GithubUsersDao
import ru.nobird.hse2021.sample.githubuserlist.data.cache.model.GithubUsersEntity

@Database(entities = [GithubUsersEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): GithubUsersDao
}
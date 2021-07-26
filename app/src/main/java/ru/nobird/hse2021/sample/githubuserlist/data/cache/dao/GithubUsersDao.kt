package ru.nobird.hse2021.sample.githubuserlist.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nobird.hse2021.sample.githubuserlist.data.cache.model.GithubUsersEntity

@Dao
interface GithubUsersDao {
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<GithubUsersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<GithubUsersEntity>)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
package com.ndewon.spendless.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ndewon.spendless.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("UPDATE users SET pin = :pinHash WHERE username = :username")
    suspend fun updatePin(username: String, pinHash: String): Int

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity?

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Long): Int
}
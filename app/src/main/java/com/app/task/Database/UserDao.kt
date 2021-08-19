package com.app.task.Database

import androidx.room.*
import com.app.task.Model.PostInfo

@Dao
interface UserDao {

    @Insert
    fun insertUser(users: PostInfo)

    @Query("Select * from user")
    fun gelAllUsers(): List<PostInfo>

    @Update
    fun updateUser(users: PostInfo)

    @Delete
    fun deleteUser(users: PostInfo)

    @Query("DELETE FROM user")
    fun deleteAll()

}
package com.example.githubuser.datasource.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.datasource.local.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<UserModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserModel)

    @Update
    fun updateUser(user: UserModel)

    @Query("SELECT * FROM UserTable WHERE username=:username")
    fun readUser(username: String): LiveData<UserModel>

    @Query("SELECT * FROM UserTable")
    fun readUsers(): LiveData<List<UserModel>>

    @Query("SELECT * FROM UserTable WHERE favorite=1")
    fun readFavoriteUsers(): LiveData<List<UserModel>>

}
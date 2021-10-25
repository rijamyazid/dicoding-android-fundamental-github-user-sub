package com.example.githubuser.datasource.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.local.room.dao.UserDao
import javax.inject.Singleton

@Singleton
@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
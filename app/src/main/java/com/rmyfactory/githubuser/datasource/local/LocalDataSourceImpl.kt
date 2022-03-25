package com.rmyfactory.githubuser.datasource.local

import androidx.lifecycle.LiveData
import com.rmyfactory.githubuser.datasource.local.model.UserModel
import com.rmyfactory.githubuser.datasource.local.room.dao.UserDao
import javax.inject.Inject

class LocalDataSourceImpl
@Inject constructor(private val userDao: UserDao) : LocalDataSource {
    override fun readUsers(): LiveData<List<UserModel>> {
        return userDao.readUsers()
    }

    override fun readUser(username: String): LiveData<UserModel> {
        return userDao.readUser(username)
    }

    override fun readFavoriteUsers(): LiveData<List<UserModel>> {
        return userDao.readFavoriteUsers()
    }

    override suspend fun updateUser(user: UserModel) {
        userDao.updateUser(user)
    }

    override suspend fun insertUser(user: UserModel) {
        userDao.insertUser(user)
    }

    override suspend fun insertUsers(users: List<UserModel>) {
        userDao.insertUsers(users)
    }
}
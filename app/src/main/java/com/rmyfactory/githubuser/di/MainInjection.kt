package com.rmyfactory.githubuser.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.rmyfactory.githubuser.datasource.datastore.MainPreferences
import com.rmyfactory.githubuser.datasource.local.LocalDataSource
import com.rmyfactory.githubuser.datasource.local.LocalDataSourceImpl
import com.rmyfactory.githubuser.datasource.local.room.dao.UserDao
import com.rmyfactory.githubuser.datasource.local.room.db.MainDatabase
import com.rmyfactory.githubuser.datasource.remote.RemoteDataSource
import com.rmyfactory.githubuser.datasource.remote.RemoteDataSourceImpl
import com.rmyfactory.githubuser.datasource.repository.MainRepository
import com.rmyfactory.githubuser.datasource.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainInjection {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideRemoteDataSource()
            : RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    fun provideLocalDataSource(userDao: UserDao)
            : LocalDataSource {
        return LocalDataSourceImpl(userDao)
    }

    @Provides
    fun provideMainRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MainRepository {
        return MainRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "GithubUsers.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: MainDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideSettingPreferences(@ApplicationContext context: Context): MainPreferences {
        return MainPreferences(context)
    }

}
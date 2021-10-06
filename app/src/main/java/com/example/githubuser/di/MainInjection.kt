package com.example.githubuser.di

import android.content.Context
import com.example.githubuser.datasource.remote.RemoteDataSource
import com.example.githubuser.datasource.remote.RemoteDataSourceImpl
import com.example.githubuser.datasource.repository.MainRepository
import com.example.githubuser.datasource.repository.MainRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainInjection {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideRemoteDataSource(@ApplicationContext context: Context, gson: Gson)
            : RemoteDataSource {
        return RemoteDataSourceImpl(context, gson)
    }

    @Provides
    fun provideMainRepository(remoteDataSource: RemoteDataSource): MainRepository {
        return MainRepositoryImpl(remoteDataSource)
    }

}
package com.example.githubuser.datasource.remote

sealed class RemoteSealed<out T> {
    data class Value<out T>(val data: T) : RemoteSealed<T>()
    data class Error(val message: String?) : RemoteSealed<Nothing>()
}
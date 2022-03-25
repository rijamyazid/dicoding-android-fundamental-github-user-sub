package com.rmyfactory.githubuser.datasource.local

sealed class LocalSealed<out T> {
    data class Value<out T>(val data: T) : LocalSealed<T>()
    data class Error(val message: String?) : LocalSealed<Nothing>()
    data class Loading(val status: Boolean?) : LocalSealed<Nothing>()
}
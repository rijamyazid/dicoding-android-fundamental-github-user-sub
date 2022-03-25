package com.rmyfactory.githubuser.util

import android.util.Log

object NetworkConstant {

    private const val TESTING_TAG = "testingLog"
    const val CODE_EMPTY = "Tidak ada data pengguna"
    const val BASE_URL = "https://api.github.com/"

    const val LIST_USER = "users"
    const val DETAIL_USER = "users/"
    const val SEARCH_USER = "search/users"
    const val FOLLOWING_USER = "following"
    const val FOLLOWERS_USER = "followers"

    fun testingLog(message: String) {
        Log.d(TESTING_TAG, message)
    }

}
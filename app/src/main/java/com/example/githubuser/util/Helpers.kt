package com.example.githubuser.util

import com.example.githubuser.R

object Helpers {

    private fun imgPair(): Map<String, Int> = mapOf(
        "@drawable/user1" to R.drawable.user1,
        "@drawable/user2" to R.drawable.user2,
        "@drawable/user3" to R.drawable.user3,
        "@drawable/user4" to R.drawable.user4,
        "@drawable/user5" to R.drawable.user5,
        "@drawable/user6" to R.drawable.user6,
        "@drawable/user7" to R.drawable.user7,
        "@drawable/user8" to R.drawable.user8,
        "@drawable/user9" to R.drawable.user9,
        "@drawable/user10" to R.drawable.user10,
    )

    fun getDrawableFromStr(str: String): Int? = imgPair()[str]

}
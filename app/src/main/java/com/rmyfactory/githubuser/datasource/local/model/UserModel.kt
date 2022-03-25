package com.rmyfactory.githubuser.datasource.local.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "UserTable", primaryKeys = ["username"])
@Parcelize
data class UserModel(
    val follower: Int,
    val following: Int,
    val name: String,
    val company: String,
    val location: String,
    val avatar: String,
    val repository: Int,
    val username: String,
    var favorite: Boolean = false
) : Parcelable

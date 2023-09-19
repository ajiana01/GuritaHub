package com.ajiananta.submissionfundamental.data

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("id")
    val id: Int
)
data class UserResponse(
    @field:SerializedName("items")
    val items: ArrayList<User>,
    @field:SerializedName("total_count")
    val totalCount: Int? = null
)


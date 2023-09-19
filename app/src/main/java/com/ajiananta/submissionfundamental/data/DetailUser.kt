package com.ajiananta.submissionfundamental.data

import com.google.gson.annotations.SerializedName

data class DetailUser(

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: String,

)

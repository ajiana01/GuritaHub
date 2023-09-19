package com.ajiananta.submissionfundamental.api

import com.ajiananta.submissionfundamental.BuildConfig
import com.ajiananta.submissionfundamental.data.DetailUser
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.KEY}")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.KEY}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.KEY}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.KEY}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}
package com.ajiananta.submissionfundamental.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    val followingList = MutableLiveData<ArrayList<User>>()

    fun setlistFollowers(username: String) {
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        followingList.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })

    }
    fun getListFollowing(): MutableLiveData<ArrayList<User>> {
        return followingList
    }
}
package com.ajiananta.submissionfundamental.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {
    val followersList = MutableLiveData<ArrayList<User>>()

    fun setlistFollowers(username: String) {
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        followersList.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })

    }
    fun getListFollowers(): MutableLiveData<ArrayList<User>> {
        return followersList
    }
}
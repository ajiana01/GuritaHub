package com.ajiananta.submissionfundamental.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.data.UserResponse
import com.ajiananta.submissionfundamental.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()
    val totalCount = MutableLiveData<Int>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                        totalCount.postValue(response.body()?.totalCount)
                        getTotalCount()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }
    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUser
    }
    fun getTotalCount(): LiveData<Int> {
        return totalCount
    }
}
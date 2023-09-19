package com.ajiananta.submissionfundamental.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ajiananta.submissionfundamental.data.DetailUser
import com.ajiananta.submissionfundamental.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailUser>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getDetailUser(username)
            .enqueue(object: Callback<DetailUser>{
                override fun onResponse(
                    call: Call<DetailUser>,
                    response: Response<DetailUser>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }
    fun getUserDetail(): MutableLiveData<DetailUser> {
        return user
    }
}
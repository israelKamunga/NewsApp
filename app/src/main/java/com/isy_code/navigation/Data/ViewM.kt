package com.isy_code.navigation.Data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewM : ViewModel() {

    val livedata : MutableLiveData<MutableList<New>> by lazy { MutableLiveData<MutableList<New>>() }
    val dataAvailable : MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)

    var data : MutableList<New> = arrayListOf()

    init {
        getAllNews()
    }

    fun initialiseRetrofitService():MediaStoreNews{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inshorts.deta.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var new = retrofit.create(MediaStoreNews::class.java);


        return new
    }

    fun getBody(response: Response<JsonObject>){
        data = arrayListOf()
        var dataList = response.body()?.get("data")?.asJsonArray?.toList()
        dataList?.forEach {it
            var new = New(
                it.asJsonObject.get("author").asString,
                it.asJsonObject.get("content").asString,
                it.asJsonObject.get("imageUrl").asString,
                it.asJsonObject.get("title").asString
            )
            data?.add(new)
        }
    }

    fun getAllNews(){

        var result = initialiseRetrofitService().getNews()

        result.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if(response!!.isSuccessful){
                    dataAvailable.value = true
                    getBody(response)
                    livedata.postValue(data)
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                dataAvailable.value = false
            }
        })
    }


    fun getSportsNews(){
        var result = initialiseRetrofitService().getSportsNews()

        result.enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                getBody(response)
                livedata.postValue(data)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {}
        })
    }
}
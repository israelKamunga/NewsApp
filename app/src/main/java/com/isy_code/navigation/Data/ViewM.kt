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

    private val livedata : MutableLiveData<ArrayList<New>> by lazy { MutableLiveData<ArrayList<New>>() }
    val resultat : LiveData<ArrayList<New>>
        get() = livedata

    val data : ArrayList<New> = arrayListOf()

    init {
        getData()
    }

    fun getData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://inshorts.deta.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var new = retrofit.create(MediaStoreNews::class.java);

        var result = new.getNews()

        result.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if(response!!.isSuccessful){
                    var new = New(
                        response.body()?.get("data")?.asJsonArray?.get(0)?.asJsonObject?.get("author")!!.asString,
                        response.body()?.get("data")?.asJsonArray?.get(0)?.asJsonObject?.get("content")!!.asString,
                        response.body()?.get("data")?.asJsonArray?.get(0)?.asJsonObject?.get("imageUrl")!!.asString,
                        response.body()?.get("data")?.asJsonArray?.get(0)?.asJsonObject?.get("title")!!.asString
                    )
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
                    livedata.postValue(data)
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {}
        })
    }
}
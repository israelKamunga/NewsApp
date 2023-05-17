package com.isy_code.navigation.Data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MediaStoreNews {
    @GET(value = "news?category=all")
    fun getNews(): Call<JsonObject>

    @GET(value = "news?category=business")
    fun getBusinessNews() : Call<JsonObject>

    @GET(value = "news?category=sports")
    fun getSportsNews() : Call<JsonObject>

    @GET(value = "news?category=world")
    fun getWorldNews() : Call<JsonObject>

    @GET(value = "news?category=politics")
    fun getPoliticsNews() : Call<JsonObject>

    @GET(value = "news?category=technology")
    fun getTechnologyNews() : Call<JsonObject>

    @GET(value = "news?category=startup")
    fun getStartupNews() : Call<JsonObject>

    @GET(value = "news?category=entertainment")
    fun getEntertainmentNews() : Call<JsonObject>

    @GET(value = "news?category=science")
    fun getScienceNews() : Call<JsonObject>

    @GET(value = "news?category=automobile")
    fun getAutomobileNews() : Call<JsonObject>

    //d7572f9c56e769f12c637c2837e49420
    //https://chroniclingamerica.loc.gov/lccn/sn86069873.json
}
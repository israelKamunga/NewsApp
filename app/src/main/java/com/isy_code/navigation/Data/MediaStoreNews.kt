package com.isy_code.navigation.Data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface MediaStoreNews {
    @GET(value = "news?category=sports")
    fun getNews(): Call<JsonObject>
    //d7572f9c56e769f12c637c2837e49420
    //https://chroniclingamerica.loc.gov/lccn/sn86069873.json
}
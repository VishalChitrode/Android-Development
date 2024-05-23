package com.example.androidarchitecturecomponents

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinGeckoApi {
    @GET("simple/price")
    fun getPrice(
        @Query("ids") ids: String?,
        @Query("vs_currencies") vsCurrencies: String?
    ): Call<JsonObject?>?
}
package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuiulder {

    // https://meme-api.herokuapp.com/gimme
    val api = "https://meme-api.herokuapp.com/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(api)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
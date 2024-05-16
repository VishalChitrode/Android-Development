package com.example.retrofit

import retrofit2.http.GET

interface myInterface {
    @GET("/quotes")
  suspend  fun getQuotes(): retrofit2.Call<List<quotablelist>>
}
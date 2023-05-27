package com.example.lab7_2

import retrofit2.http.GET

interface InfoAPI {
    @GET("v1/bpi/currentprice.json")
    suspend fun getInformation(): Info
}
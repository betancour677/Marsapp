package com.example.marsapp.Model


import retrofit2.http.GET

interface MarsApi {
    @GET("realestate")
    suspend fun fecthMarsList(): List<MarsApiClass>

}
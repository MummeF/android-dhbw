package com.dhbw.mummefelix.uebung05.api

import com.dhbw.mummefelix.uebung05.api.model.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("/api")
    fun getRandomUser():Call<RandomUserResponse>
    @GET("/api")
    fun getGenderOnly(@Query("gender") gender: String):Call<RandomUserResponse>
}
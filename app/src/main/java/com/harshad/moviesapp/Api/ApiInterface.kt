package com.harshad.moviesapp.Api

import com.harshad.moviesapp.Model.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/1.json")
    suspend fun getFirstData():Response<Movies>;

    @GET("/2.json")
    suspend fun getSecondData():Response<Movies>;


}
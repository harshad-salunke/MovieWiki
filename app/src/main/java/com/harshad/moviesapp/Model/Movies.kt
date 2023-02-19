package com.harshad.moviesapp.Model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Movie List")
    val movies:ArrayList<Movie>)

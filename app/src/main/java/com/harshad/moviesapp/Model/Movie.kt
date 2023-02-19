package com.harshad.moviesapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie (
     @PrimaryKey
     val IMDBID:String,
     val Title:String,
     val Year:String,
     val Summary:String,
     @SerializedName("Short Summary")
     @ColumnInfo(name = "Short_Summary", defaultValue = "temp")
     val Short_Summary:String,
     val Genres:String,
     val Runtime:String,
     @SerializedName("YouTube Trailer")
     @ColumnInfo(name = "YouTube_Trailer", defaultValue = "temp")
     val YouTube_Trailer:String,
     val Rating:String,
     @SerializedName("Movie Poster")
     @ColumnInfo(name = "Movie_Poster", defaultValue = "temp")
     val Movie_Poster:String,
     val Director:String,
     val Writers:String,
     val Cast:String
)
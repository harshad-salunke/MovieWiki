package com.harshad.moviesapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harshad.moviesapp.Model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao():RoomDao

companion object {
    private var INSTANCE: MovieDataBase? = null
    fun getInstance(context: Context): MovieDataBase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, "movieDB").build();
        }
        return INSTANCE!!
    }
  }
}
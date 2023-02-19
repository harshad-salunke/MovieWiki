package com.harshad.moviesapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harshad.moviesapp.Model.Movie

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie:List<Movie>)


    @Query("select * from movies")
      fun  getMovie():List<Movie>
}
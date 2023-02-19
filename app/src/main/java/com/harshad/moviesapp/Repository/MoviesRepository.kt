package com.harshad.moviesapp.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harshad.moviesapp.Api.ApiInterface
import com.harshad.moviesapp.Model.Movie
import com.harshad.moviesapp.Model.Movies
import com.harshad.moviesapp.room.MovieDataBase
import com.harshad.moviesapp.utils.NetConnectivity

class MoviesRepository(
    private val apiInterface: ApiInterface,
    private val movieDataBase: MovieDataBase,
private  val applicationContext:Context) {

    val mutableLiveData=MutableLiveData<Movies>();
    val liveData_movies:LiveData<Movies>
        get()=mutableLiveData

    suspend fun getMovies(){
        if(NetConnectivity.checkForInternet(applicationContext)){
            val result=apiInterface.getData();
            if(result.body()!=null){
                movieDataBase.movieDao().insertMovie(result.body()!!.movies)
                mutableLiveData.postValue(result.body())
            }else{
                Log.d("harshad","no data");
            }
        }else{
            var offline_movie_list=movieDataBase.movieDao().getMovie();
            var offline_movies=Movies(offline_movie_list as ArrayList<Movie>)
            mutableLiveData.postValue(offline_movies)
        }

    }
}
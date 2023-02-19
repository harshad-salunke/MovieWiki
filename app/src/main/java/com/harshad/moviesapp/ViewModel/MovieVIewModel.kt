package com.harshad.moviesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshad.moviesapp.Model.Movies
import com.harshad.moviesapp.Repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieVIewModel (private val moviesRepository: MoviesRepository):ViewModel(){
    init {
        viewModelScope.launch(Dispatchers.IO){
            moviesRepository.getMovies()
        }
    }
    val movies_data:LiveData<Movies>
    get()=moviesRepository.liveData_movies


}
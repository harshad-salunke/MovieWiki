package com.harshad.moviesapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshad.moviesapp.Repository.MoviesRepository

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieVIewModel(moviesRepository) as T
    }
}


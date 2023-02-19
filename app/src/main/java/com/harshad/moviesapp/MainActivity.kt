package com.harshad.moviesapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harshad.moviesapp.Api.ApiInterface
import com.harshad.moviesapp.Api.ApiUtilities
import com.harshad.moviesapp.Model.Movie
import com.harshad.moviesapp.RecyclerAdapters.RecyclerMovieAdapter
import com.harshad.moviesapp.Repository.MoviesRepository
import com.harshad.moviesapp.ViewModel.MovieVIewModel
import com.harshad.moviesapp.ViewModel.MoviesViewModelFactory
import com.harshad.moviesapp.room.MovieDataBase
import com.harshad.moviesapp.utils.NetConnectivity
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var movieVIewModel: MovieVIewModel;
  private  lateinit var movies:ArrayList<Movie>;
    private  lateinit var Main_movies:ArrayList<Movie>;

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerMovieAdapter:RecyclerMovieAdapter
    private lateinit var netConnectivit:LinearLayout
    private lateinit var close_internet_view:ImageView
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initviews();
       getDataFromDatabaseOrRetrofit();

        close_internet_view.setOnClickListener(View.OnClickListener {
            netConnectivit.visibility=View.GONE
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    searchView.clearFocus()
                    movies.clear()
                    movies.addAll(Main_movies)
                    recyclerMovieAdapter.updateRecyclerviewData(movies)
                } else {
                    filter(newText)
                }
                return false
            }
        })



    }


    private fun getDataFromDatabaseOrRetrofit() {
        val apiInterface=ApiUtilities.getInstance().create(ApiInterface::class.java)
        val moviesDatabase=MovieDataBase.getInstance(applicationContext)
        val moviesRepository=MoviesRepository(apiInterface,moviesDatabase,this)
        movieVIewModel=ViewModelProvider(this,MoviesViewModelFactory(moviesRepository)).get(MovieVIewModel::class.java);
        movieVIewModel.movies_data.observe(this) {
            Main_movies = it.movies;
            movies.addAll(Main_movies)
            recyclerMovieAdapter.updateRecyclerviewData(movies)
        }
    }

    private fun initviews() {
        searchView=findViewById(R.id.search_view)
        recyclerView=findViewById(R.id.recycler_view)
        netConnectivit=findViewById(R.id.bottom_internet_view)
        close_internet_view=findViewById(R.id.internet_close)
        movies= ArrayList<Movie>()
        Main_movies= ArrayList<Movie>()
        recyclerMovieAdapter=RecyclerMovieAdapter(this,movies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=recyclerMovieAdapter

        if (NetConnectivity.checkForInternet(this)==false){
            netConnectivit.visibility=View.VISIBLE
        }
    }

    private fun filter(text: String) {

        val filteredlist: ArrayList<Movie> = ArrayList<Movie>()
        for (item in movies) {
            if (item.Title.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        Log.d("filterList",filteredlist.toString())
        if (filteredlist.isEmpty()) {
        }else{
            recyclerMovieAdapter.updateRecyclerviewData(filteredlist)

        }
    }

}
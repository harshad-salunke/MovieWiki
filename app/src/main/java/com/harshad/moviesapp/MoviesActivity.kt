package com.harshad.moviesapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.gson.Gson
import com.harshad.moviesapp.Model.Movie
import com.ms.square.android.expandabletextview.ExpandableTextView


class MoviesActivity : AppCompatActivity() {
    lateinit var expandableview:ExpandableTextView;
    lateinit var movie_image:ImageView
    lateinit var movie_name:TextView
    lateinit var movie_year:TextView
    lateinit var movie_time:TextView
    lateinit var movie_genre:TextView
    lateinit var movie_rating:TextView
    lateinit var back_btn:CardView
    lateinit var play_trailer:CardView
 lateinit   var movie_Data:Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val movieJson:String = intent.getStringExtra("movie_obj").toString()
        movie_Data = Gson().fromJson(movieJson, Movie::class.java)
        initView();


        Glide.with(this).
        load(movie_Data.Movie_Poster)
            .apply(RequestOptions.fitCenterTransform()
                .placeholder(R.drawable.place_holder)

                .signature(ObjectKey(System.currentTimeMillis())))
            .into(movie_image)
        movie_name.setText(movie_Data.Title)
        movie_genre.setText(movie_Data.Genres)
        expandableview.setText(movie_Data.Summary)
        movie_year.setText(movie_Data.Year)
        movie_rating.setText(movie_Data.Rating)
        movie_time.setText(convertSecondsToMinutesAndSeconds(movie_Data.Runtime.toInt())+"min")

        back_btn.setOnClickListener(View.OnClickListener {
            finish()
        })

        play_trailer.setOnClickListener(View.OnClickListener {
            var intent:Intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+movie_Data.YouTube_Trailer));
            startActivity(intent)

        })

    }
    private fun convertSecondsToMinutesAndSeconds(secondsInput: Int): String? {
        val minutes = secondsInput / 60
        val seconds = secondsInput % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
    private fun initView() {
        expandableview=findViewById(R.id.expand_text_view);
        movie_image=findViewById(R.id.movie_image)
        movie_name=findViewById(R.id.movie_name)
        movie_year=findViewById(R.id.movie_year)
        movie_time=findViewById(R.id.movie_time)
        movie_rating=findViewById(R.id.movie_rating)
        movie_genre=findViewById(R.id.movie_genre)
        back_btn=findViewById(R.id.back_btn)
        play_trailer=findViewById(R.id.play_on_youtube)
    }
}
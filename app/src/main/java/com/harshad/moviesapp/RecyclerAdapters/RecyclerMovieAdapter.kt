package com.harshad.moviesapp.RecyclerAdapters

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.gson.Gson
import com.harshad.moviesapp.Model.Movie
import com.harshad.moviesapp.MoviesActivity
import com.harshad.moviesapp.R

class RecyclerMovieAdapter(var context:Context,var arrayList:ArrayList<Movie>): RecyclerView.Adapter<RecyclerMovieAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("priiiiiiii",arrayList.toString())

        var view=LayoutInflater.from(context).inflate(R.layout.movie_card,parent,false);
   return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie:Movie=arrayList.get(position)
        Log.d("priiiiiiii",movie.toString())
        holder.movie_name.setText(movie.Title);
        holder.genre.setText(movie.Genres);
        holder.year.setText(movie.Year);
        holder.rating.setText(movie.Rating);

        Glide.with(context)
            .load(movie.Movie_Poster)
            .apply(RequestOptions.fitCenterTransform()
                .placeholder(R.drawable.place_holder)
                .signature(ObjectKey(System.currentTimeMillis())))
            .into(holder.movie_poster)
        holder.watch_button.setOnClickListener(View.OnClickListener {
            val intent:Intent = Intent(context, MoviesActivity::class.java)
            val jsonString = Gson().toJson(movie)

            intent.putExtra("movie_obj",jsonString);
            context.startActivity(intent)
        })
    }
fun updateRecyclerviewData( movielist:ArrayList<Movie>){
    arrayList=movielist
    notifyDataSetChanged()
}
    override fun getItemCount(): Int {
        return arrayList.size;
    }

    class ViewHolder( itemView:View) : RecyclerView.ViewHolder(itemView) {
        var movie_name:TextView=itemView.findViewById(R.id.movie_name);
        var rating:TextView=itemView.findViewById(R.id.rating);
        var year:TextView=itemView.findViewById(R.id.year);
        var genre:TextView=itemView.findViewById(R.id.genres);
        var movie_poster:ImageView=itemView.findViewById(R.id.movie_poster);
        var watch_button:Button=itemView.findViewById(R.id.watch_now);

    }
}
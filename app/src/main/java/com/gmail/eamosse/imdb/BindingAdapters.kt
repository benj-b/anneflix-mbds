package com.gmail.eamosse.imdb

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.imdb.ui.home.MovieAdapter

@BindingAdapter("bindMovies")
fun bindMovies(recyclerView: RecyclerView, movies: List<Movie>?) {
    // Check if the movie list is not null
    movies?.let { it ->
        // Create or update the RecyclerView adapter
        val adapter = recyclerView.adapter as? MovieAdapter ?: MovieAdapter(it) { id ->
            println("MovieAdapter: Clicked on movieId: $id")
        }
        recyclerView.adapter = adapter
        adapter.movies = it
    }
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    // Utilisez une bibliothèque comme Picasso ou Glide pour charger l'image depuis l'URL
    if (!imageUrl.isNullOrBlank()) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}
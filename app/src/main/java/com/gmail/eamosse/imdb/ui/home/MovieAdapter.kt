package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.imdb.databinding.MovieListItemBinding

// MovieAdapter.kt
class MovieAdapter(
    var movies: List<Movie>,
    private val onMovieClickListener: (Movie) ->  Unit
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // ViewHolder et méthodes onCreateViewHolder/onBindViewHolder similaires à CategoryAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])

    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.root.setOnClickListener { onMovieClickListener(movie) }
        }
    }
}

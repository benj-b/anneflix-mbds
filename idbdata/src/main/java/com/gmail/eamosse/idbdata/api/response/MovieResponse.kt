package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val results: List<MovieItem>
) {
    data class MovieItem(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("poster_path") val posterPath: String
    ) {
        fun toMovie(): Movie {
            return Movie(id, title, "https://image.tmdb.org/t/p/original$posterPath", overview)
        }
    }
}
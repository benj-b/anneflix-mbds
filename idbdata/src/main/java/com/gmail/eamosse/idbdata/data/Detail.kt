package com.gmail.eamosse.idbdata.data

import com.google.gson.annotations.SerializedName

data class Detail(
    val title: String,
    val backdropPath: String,
    val genres: List<Genre>,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val productionCompanies: List<Company>,
    val productionCountries: List<Country>,
    val releaseDate: String,
    val budget: Int,
    val revenue: Int,
    val status: String,
    val tagline: String,
    val voteAverage: Float,
    val voteCount: Int
)

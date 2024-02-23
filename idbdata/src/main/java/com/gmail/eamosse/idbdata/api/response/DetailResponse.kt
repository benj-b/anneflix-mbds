package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Company
import com.gmail.eamosse.idbdata.data.Country
import com.gmail.eamosse.idbdata.data.Detail
import com.gmail.eamosse.idbdata.data.Genre
import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("title") val title: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: List<Company>,
    @SerializedName("production_countries") val production_countries: List<Country>,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("revenue") val revenue: Int,

    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("vote_count") val vote_count: Int
) {
    fun toDetail(): Detail {
        return Detail(
            title,
            "https://image.tmdb.org/t/p/original$backdrop_path",
            genres,
            overview,
            popularity,
            "https://image.tmdb.org/t/p/original$poster_path",
            production_companies,
            production_countries,
            release_date,
            budget,
            revenue,
            status,
            tagline,
            vote_average,
            vote_count
        )
    }
}
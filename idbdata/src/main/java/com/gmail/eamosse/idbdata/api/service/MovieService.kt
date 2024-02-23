package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DetailResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("/3/genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("/3/discover/movie")
    suspend fun getMoviesByCategory(
        @Query("with_genres") categoryId: String
    ): Response<MovieResponse>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int
    ): Response<DetailResponse>

    @GET("/3/search/movie")
    suspend fun getMoviesBySearch(
        @Query("query") searchTerm: String
    ): Response<MovieResponse>

    @GET("/3/movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") movieId: Int
    ): Response<VideoResponse>
}
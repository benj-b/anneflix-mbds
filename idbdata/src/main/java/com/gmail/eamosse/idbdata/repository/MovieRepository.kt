package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Detail
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.data.Video
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import javax.inject.Inject

/**
 * La classe permettant de gérer les données de l'application
 */

class MovieRepository @Inject constructor(
    private val local: LocalDataSource,
    private val online: OnlineDataSource
) {

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data)
                //return the response
                Result.Succes(result.data)
            }

            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when (val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }

            is Result.Error -> result
        }
    }

    suspend fun getMoviesByCategory(categoryId: String): Result<List<Movie>> {
        return when (val result = online.getMoviesByCategory(categoryId)) {
            is Result.Succes -> {
                val movies = result.data.results.map {
                    it.toMovie()
                }
                Result.Succes(movies)
            }

            is Result.Error -> result
        }
    }

    suspend fun getPopularMovies(): Result<List<Movie>> {
        return when (val result = online.getPopularMovies()) {
            is Result.Succes -> {
                val movies = result.data.results.map {
                    it.toMovie()
                }
                Result.Succes(movies)
            }

            is Result.Error -> result
        }
    }

    suspend fun getMovieDetails(movieId: Int): Result<Detail> {
        return when (val result = online.getMovieDetails(movieId)) {
            is Result.Succes -> {
                val detail = result.data.toDetail()
                Result.Succes(detail)
            }

            is Result.Error -> {
                return result
            }
        }
    }
    suspend fun getMoviesBySearch(searchTerm: String): Result<List<Movie>> {
        return when (val result = online.getMoviesBySearch(searchTerm)) {
            is Result.Succes -> {
                val movies = result.data.results.map {
                    it.toMovie()
                }
                Result.Succes(movies)
            }

            is Result.Error -> result
        }
    }

    suspend fun getVideos(movieId: Int): Result<List<Video>> {
        return when (val result = online.getVideos(movieId)) {
            is Result.Succes -> {
                val video = result.data.results.map {
                    it.toVideo()
                }
                Result.Succes(video)
            }

            is Result.Error -> {
                return result
            }
        }
    }
}

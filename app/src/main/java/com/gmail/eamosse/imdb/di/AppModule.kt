package com.gmail.eamosse.imdb.di

import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Named ("API_KEY")
    @Provides
    fun provideApiKey() = "7a929fc06770d0a6d26b753a60c187bd"

    @Named ("BASE_URL")
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideMovieRepository(
        local: LocalDataSource,
        online: OnlineDataSource
    ): MovieRepository {
        return MovieRepository(local, online)
    }

}
/*
val appModule = module {
    single(named("API_KEY")) {
        "507a86e6d98ae2b2cd600e594ee02637"
    }

    single(named("BASE_URL")) {
        "https://api.themoviedb.org/3/"
    }

    single(named("APP_PREFS")) {
        androidContext().getSharedPreferences("app_private", Context.MODE_PRIVATE)
    }

    viewModel {
        HomeViewModel(repository = get())
    }
}*/
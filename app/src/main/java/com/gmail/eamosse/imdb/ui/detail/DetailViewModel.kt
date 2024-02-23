package com.gmail.eamosse.imdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Detail
import com.gmail.eamosse.idbdata.data.Video
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()

    private val _details : MutableLiveData<Detail> = MutableLiveData()
    val detail: LiveData<Detail> get() = _details

    private val _videos : MutableLiveData<Video> = MutableLiveData()
    val videos: LiveData<Video> get() = _videos
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = movieRepository.getMovieDetails(movieId)) {
                is Result.Succes -> {
                    _details.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getVideos(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = movieRepository.getVideos(movieId)) {
                is Result.Succes -> {
                    _videos.postValue(result.data.first())
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}
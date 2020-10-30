package com.arthur.android.tmdb.movieDetails

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arthur.android.tmdb.movieDetails.model.MovieDetails
import com.arthur.android.tmdb.network.CustomScheduler
import com.arthur.android.tmdb.utils.ErrorHandler
import com.arthur.android.tmdb.utils.mvvm.RxViewModel
import timber.log.Timber

class MovieDetailsViewModel(
    private val repo: MovieRepository,
    private val customScheduler: CustomScheduler,
    private val errorHandler: ErrorHandler
) :
    RxViewModel(), LifecycleObserver {

    val movieId = MutableLiveData<Int>()
    val movie = MutableLiveData<MovieDetails>()
    val progressBarVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        movieId.observeForever {
            it?.let { movieId ->
                getMovieDetails(movieId)
            }
        }
    }

    private fun getMovieDetails(id: Int) {
        progressBarVisibility.value = View.VISIBLE
        launch {
            repo.getMovieDetails(id)
                .subscribeOn(customScheduler.io())
                .observeOn(customScheduler.ui())
                .subscribe({
                    movie.value = it
                    progressBarVisibility.value = View.GONE
                    Timber.d(it.toString())
                },
                    { error ->
                        error.message?.let {
                            Timber.e(it)
                            errorHandler.showError(it)
                        }
                    })
        }
    }
}

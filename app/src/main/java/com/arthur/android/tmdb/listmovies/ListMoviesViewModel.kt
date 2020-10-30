package com.arthur.android.tmdb.listmovies

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arthur.android.tmdb.listmovies.model.Movie
import com.arthur.android.tmdb.network.CustomScheduler
import com.arthur.android.tmdb.utils.ErrorHandler
import com.arthur.android.tmdb.utils.mvvm.RxViewModel
import timber.log.Timber

class ListMoviesViewModel(
    private val repo: ListMoviesRepository,
    private val customScheduler: CustomScheduler,
    private val errorHandler: ErrorHandler
) :
    RxViewModel(), LifecycleObserver {

    val resultsList: MutableLiveData<List<Movie>> = MutableLiveData()
    val progressBarVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getMoviesResponse()
    }

    private fun getMoviesResponse() {
        showProgressBar()

        launch {
            repo.getMovies()
                .subscribeOn(customScheduler.io())
                .observeOn(customScheduler.ui())
                .subscribe({
                    Timber.d(it.toString())
                    resultsList.value = it
                    hideProgressBar()
                }, { error ->
                    error.message?.let {
                        Timber.e(it)
                        errorHandler.showError(it)
                    }
                })
        }
    }

    private fun showProgressBar() {
        progressBarVisibility.value = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBarVisibility.value = View.GONE
    }
}
package com.arthur.android.tmdb.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arthur.android.tmdb.network.CustomScheduler
import com.arthur.android.tmdb.utils.ErrorHandler
import com.arthur.android.tmdb.utils.mvvm.RxViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class SearchViewModel(
    private val repo: SearchRepository,
    private val customScheduler: CustomScheduler,
    private val errorHandler: ErrorHandler,
    private val searchProvider: SearchProvider
) : RxViewModel(), LifecycleObserver {

    val searchList: MutableLiveData<List<SearchMovie>> = MutableLiveData()
    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        disposables += searchProvider.searchQueries.subscribeBy { query ->
            if (query.isNotEmpty()) {
                getMoviesResponse(query)
            }

            Timber.d(query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun getMoviesResponse(query: String) {

        launch {
            repo.search(query)
                .subscribeOn(customScheduler.io())
                .observeOn(customScheduler.ui())
                .subscribe({
                    Timber.d(it.toString())
                    searchList.value = it
                }, { error ->
                    error.message?.let {
                        Timber.e(it)
                        errorHandler.showError(it)
                    }
                })
        }
    }
}

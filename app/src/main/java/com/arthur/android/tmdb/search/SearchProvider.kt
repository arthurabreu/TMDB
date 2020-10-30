package com.arthur.android.tmdb.search

import io.reactivex.subjects.PublishSubject

class SearchProvider {

    val searchQueries = PublishSubject.create<String>()

    fun onTextChange(query: String?) {
        searchQueries.onNext(query ?: "")
    }

    fun onTextSubmit(query: String?) {
    }
}
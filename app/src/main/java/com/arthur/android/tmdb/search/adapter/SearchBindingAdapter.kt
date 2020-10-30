package com.arthur.android.tmdb.search.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arthur.android.tmdb.search.SearchMovie

@BindingAdapter("searchList")
fun RecyclerView.bind(movies: List<SearchMovie>?) {

    movies?.let { val adapter = adapter as SearchAdapter
        adapter.updateSearchList(movies) }
}
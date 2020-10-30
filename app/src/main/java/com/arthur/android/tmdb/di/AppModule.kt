package com.arthur.android.tmdb.di

import com.arthur.android.tmdb.listmovies.ListMoviesFragment
import com.arthur.android.tmdb.listmovies.ListMoviesRepository
import com.arthur.android.tmdb.listmovies.ListMoviesViewModel
import com.arthur.android.tmdb.movieDetails.MovieDetailsViewModel
import com.arthur.android.tmdb.movieDetails.MovieRepository
import com.arthur.android.tmdb.network.CustomScheduler
import com.arthur.android.tmdb.network.NetworkClient
import com.arthur.android.tmdb.search.SearchFragment
import com.arthur.android.tmdb.search.SearchProvider
import com.arthur.android.tmdb.search.SearchRepository
import com.arthur.android.tmdb.search.SearchViewModel
import com.arthur.android.tmdb.utils.ErrorHandler
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single { CustomScheduler() }
    factory { NetworkClient().networkResponse }
    single { ErrorHandler(get()) }

    single { ListMoviesRepository(get()) }
    single { MovieRepository(get()) }
    single { SearchRepository(get()) }

    single { ListMoviesFragment() }
    single { SearchFragment() }

    single { SearchProvider() }

    viewModel { ListMoviesViewModel(get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get(), get()) }
}

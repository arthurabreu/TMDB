package com.arthur.android.tmdb.search

import com.arthur.android.tmdb.data.SearchMovieMapper
import com.arthur.android.tmdb.network.NetworkInterface
import com.arthur.android.tmdb.network.models.listMovies.ConfigurationEntity
import com.arthur.android.tmdb.network.models.listMovies.MoviesResponseEntity
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class SearchRepository(private val networkClient: NetworkInterface) {

    fun search(query: String): Observable<List<SearchMovie>> {
        return Observables.zip(searchMovie(query), getConfiguration()) { movies, configuration ->
            SearchMovieMapper().mapFromEntity(movies, configuration.images)
        }
    }

    private fun searchMovie(query: String): Observable<MoviesResponseEntity> = networkClient.searchMovies(query)

    private fun getConfiguration(): Observable<ConfigurationEntity> = networkClient.getConfiguration()
}
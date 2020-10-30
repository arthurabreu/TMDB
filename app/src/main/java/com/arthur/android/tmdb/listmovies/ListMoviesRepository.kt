package com.arthur.android.tmdb.listmovies

import com.arthur.android.tmdb.data.MoviesMapper
import com.arthur.android.tmdb.listmovies.model.Movie
import com.arthur.android.tmdb.network.NetworkInterface
import com.arthur.android.tmdb.network.models.listMovies.ConfigurationEntity
import com.arthur.android.tmdb.network.models.listMovies.MoviesResponseEntity
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class ListMoviesRepository(private val networkClient: NetworkInterface) {

    fun getMovies(): Observable<List<Movie>> {
        return Observables.zip(showMovies(), getConfiguration()) { movies, configuration ->
            MoviesMapper().mapFromEntity(movies, configuration.images)
        }
    }

    private fun showMovies(): Observable<MoviesResponseEntity> = networkClient.getListMovies()

    private fun getConfiguration(): Observable<ConfigurationEntity> = networkClient.getConfiguration()
}

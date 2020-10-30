package com.arthur.android.tmdb.movieDetails

import com.arthur.android.tmdb.data.MovieDetailsMapper
import com.arthur.android.tmdb.movieDetails.model.MovieDetails
import com.arthur.android.tmdb.network.NetworkInterface
import com.arthur.android.tmdb.network.models.listMovies.ConfigurationEntity
import com.arthur.android.tmdb.network.models.movieDetails.MovieDetailsEntity
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class MovieRepository(private val networkClient: NetworkInterface) {

    fun getMovieDetails(id: Int): Observable<MovieDetails> {
        return Observables.zip(showMovieDetails(id), getConfiguration()) { movie, configuration ->
            MovieDetailsMapper().mapFromEntity(movie, configuration.images)
        }
    }

    private fun showMovieDetails(id: Int): Observable<MovieDetailsEntity> = networkClient.getMovieDetails(id)

    private fun getConfiguration(): Observable<ConfigurationEntity> = networkClient.getConfiguration()
}

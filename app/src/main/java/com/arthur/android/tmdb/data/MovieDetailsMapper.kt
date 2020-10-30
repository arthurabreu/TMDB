package com.arthur.android.tmdb.data

import com.arthur.android.tmdb.movieDetails.model.MovieDetails
import com.arthur.android.tmdb.network.models.listMovies.ImagesEntity
import com.arthur.android.tmdb.network.models.movieDetails.MovieDetailsEntity
import com.arthur.android.tmdb.utils.ORIGINAL_LOGO_SIZE

class MovieDetailsMapper {
    fun mapFromEntity(movieDetailsEntity: MovieDetailsEntity, imagesEntity: ImagesEntity) =
        MovieDetails().apply {
            backdropPath = movieDetailsEntity.backdropPath
            movieDetailsEntity.genres.forEach { this.genre = this.genre + " " + it.name }
            id = movieDetailsEntity.id
            overview = movieDetailsEntity.overview
            posterPath = movieDetailsEntity.posterPath
            releaseDate = movieDetailsEntity.releaseDate
            title = movieDetailsEntity.title
            voteAverage = movieDetailsEntity.voteAverage
            completeImageUrl = imagesEntity.baseUrl + imagesEntity.logoSizes[ORIGINAL_LOGO_SIZE] +
                movieDetailsEntity.posterPath
        }
}
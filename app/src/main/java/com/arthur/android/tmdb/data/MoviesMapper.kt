package com.arthur.android.tmdb.data

import com.arthur.android.tmdb.listmovies.model.Movie
import com.arthur.android.tmdb.network.models.listMovies.ImagesEntity
import com.arthur.android.tmdb.network.models.listMovies.MoviesResponseEntity
import com.arthur.android.tmdb.utils.ORIGINAL_LOGO_SIZE

class MoviesMapper {
    fun mapFromEntity(moviesResponseEntity: MoviesResponseEntity, imagesEntity: ImagesEntity) =
        mutableListOf<Movie>().apply {
            moviesResponseEntity.results.forEach {
                Movie().run {
                    id = it.id
                    title = it.title
                    releaseDate = it.releaseDate
                    posterPath = it.posterPath
                    adult = it.adult
                    genreIds = it.genreIds
                    originalLanguage = it.originalLanguage
                    originalTitle = it.originalTitle
                    overview = it.overview
                    popularity = it.popularity
                    video = it.video
                    voteAverage = it.voteAverage
                    voteCount = it.voteCount
                    completeImageUrl = imagesEntity.baseUrl + imagesEntity.logoSizes[ORIGINAL_LOGO_SIZE] + posterPath

                    add(this)
                }
            }
        }
}
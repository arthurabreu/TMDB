package com.arthur.android.tmdb.data

import com.arthur.android.tmdb.network.models.listMovies.ImagesEntity
import com.arthur.android.tmdb.network.models.listMovies.MoviesResponseEntity
import com.arthur.android.tmdb.search.SearchMovie
import com.arthur.android.tmdb.utils.ORIGINAL_LOGO_SIZE

class SearchMovieMapper {
    fun mapFromEntity(moviesResponseEntity: MoviesResponseEntity, imagesEntity: ImagesEntity) =
        mutableListOf<SearchMovie>().apply {
            moviesResponseEntity.results.forEach {
                SearchMovie().run {
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
package com.arthur.android.tmdb.movieDetails.model

data class MovieDetails(
    var backdropPath: String = "",
    var genre: String = "",
    var id: Int = 0,
    var overview: String = "",
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var voteAverage: Double = 0.0,
    var completeImageUrl: String = ""
)
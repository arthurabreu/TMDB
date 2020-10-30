package com.arthur.android.tmdb.network.models.movieDetails

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String
)
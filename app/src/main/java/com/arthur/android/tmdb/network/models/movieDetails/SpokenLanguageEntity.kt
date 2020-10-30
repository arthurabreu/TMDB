package com.arthur.android.tmdb.network.models.movieDetails

import com.google.gson.annotations.SerializedName

data class SpokenLanguageEntity(
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)
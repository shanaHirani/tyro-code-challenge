package com.jetbrains.handson.mpp.tyrocodechallenge.netWork

import com.squareup.moshi.Json

data class MovieList(
    @Json(name = "Search") val movieList: List<Movie>?
)

data class Movie(
    @Json(name = "Title") val title: String?,
    @Json(name = "Year") val year: String?,
    @Json(name = "imdbID") val imdbID: String?,
    @Json(name = "Type") val type: String?,
    @Json(name = "Poster") val imgURL: String?,
)
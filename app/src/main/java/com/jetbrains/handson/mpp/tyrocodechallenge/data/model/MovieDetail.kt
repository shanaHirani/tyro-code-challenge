package com.jetbrains.handson.mpp.tyrocodechallenge.data.model

import com.squareup.moshi.Json

data class MovieDetail(
        @Json(name = "Title") val title: String?,
        @Json(name = "Year") val year: String?,
        @Json(name = "Runtime") val runtime: String?,
        @Json(name = "Actors") val actors: String?,
        @Json(name = "Plot") val Plot: String?,
        @Json(name = "Poster") val imgURL: String?)
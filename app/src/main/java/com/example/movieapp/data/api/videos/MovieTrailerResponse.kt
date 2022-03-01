package com.example.movieapp.data.api.videos

import com.google.gson.annotations.SerializedName


data class MovieTrailerResponse(
    val id: Int,
    @field:SerializedName("results")
    val resultTrailerResponses: List<ResultTrailerResponse>
)
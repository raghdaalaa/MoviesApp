package com.example.movieapp.data.api.model


import com.google.gson.annotations.SerializedName

data class ProductionCountryX(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String
)
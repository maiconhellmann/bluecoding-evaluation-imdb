package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCountryPayload(
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String
)
package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanyPayload(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)
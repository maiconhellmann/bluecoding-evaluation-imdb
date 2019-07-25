package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguagePayload(
    @SerializedName("iso_639_1")
    val iso: String,
    val name: String
)
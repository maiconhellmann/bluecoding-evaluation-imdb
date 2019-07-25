package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductionCompanyCache(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)
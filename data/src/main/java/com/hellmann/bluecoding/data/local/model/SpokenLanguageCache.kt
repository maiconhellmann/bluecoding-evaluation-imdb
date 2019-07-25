package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpokenLanguageCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val iso: String,
    val name: String
)
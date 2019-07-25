package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String
)
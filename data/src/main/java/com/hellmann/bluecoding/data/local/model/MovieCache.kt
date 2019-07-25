package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCache(

    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: Any,
    val budget: Int,
    val genres: List<GenreCache>,
    val homepage: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int=0,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyCache>,
    val productionCountries: List<ProductionCountryCache>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageCache>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
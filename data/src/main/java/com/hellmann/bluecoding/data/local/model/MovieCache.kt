package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCache(

    val adult: Boolean,
    val backdropPath: String?= null,
//    val belongsToCollection: Any,
    val budget: Int,
//    val genres: List<GenreCache>,
    val homepage: String? =null,
    @PrimaryKey(autoGenerate = false)
    val id: Int=0,
    val imdbId: String?= null,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
//    val productionCompanies: List<ProductionCompanyCache>,
//    val productionCountries: List<ProductionCountryCache>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
//    val spokenLanguages: List<SpokenLanguageCache>,
    val status: String?= null,
    val tagline: String?= null,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
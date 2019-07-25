package com.hellmann.bluecoding.domain.entity

data class Movie(
    val adult: Boolean,
    val backdropPath: String?= null,
    val belongsToCollection: Any? = null,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String? = null,
    val id: Int,
    val imdbId: String?= null,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String?= null,
    val tagline: String?= null,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
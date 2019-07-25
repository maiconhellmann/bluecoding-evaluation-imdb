package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hellmann.bluecoding.data.local.model.GenreCache
import com.hellmann.bluecoding.data.local.model.ProductionCompanyCache
import com.hellmann.bluecoding.data.local.model.ProductionCountryCache
import com.hellmann.bluecoding.data.local.model.SpokenLanguageCache

data class MoviePayload(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?= null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any?= null,
    val budget: Int,
    val genres: List<GenreCache>,
    val homepage: String?= null,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?= null,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyCache>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryCache>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageCache>,
    val status: String?= null,
    val tagline: String?= null,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)
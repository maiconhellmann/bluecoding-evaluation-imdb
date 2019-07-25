package com.hellmann.bluecoding.data.local.mapper

import com.hellmann.bluecoding.data.local.model.MovieCache
import com.hellmann.bluecoding.domain.entity.Movie

object MovieCacheMapper {
    fun map(cacheData: List<MovieCache>) = cacheData.map { map(it) }

    private fun map(cacheData: MovieCache) = Movie(
        adult = cacheData.adult,
        backdropPath = cacheData.backdropPath,
        belongsToCollection = cacheData.belongsToCollection,
        budget = cacheData.budget,
        genres = emptyList(), //TODO cacheData.genres mapper
        homepage = cacheData.homepage,
        id = cacheData.id,
        imdbId = cacheData.imdbId,
        originalLanguage = cacheData.originalLanguage,
        originalTitle = cacheData.originalTitle,
        overview = cacheData.overview,
        popularity = cacheData.popularity,
        posterPath = cacheData.posterPath,
        productionCompanies = emptyList(), //TODO list mapper
        productionCountries = emptyList(), //TODO list mapper
        releaseDate = cacheData.releaseDate,
        revenue = cacheData.revenue,
        runtime = cacheData.runtime,
        spokenLanguages = emptyList(), //TODO cacheData.spokenLanguages
        status = cacheData.status,
        tagline = cacheData.tagline,
        title = cacheData.title,
        video = cacheData.video,
        voteAverage = cacheData.voteAverage,
        voteCount = cacheData.voteCount)

    fun mapToCache(Movies: List<Movie>) = Movies.map { map(it) }

    private fun map(movie: Movie) = MovieCache(
        adult = movie.adult,
        backdropPath = movie.backdropPath,
        belongsToCollection = movie.belongsToCollection,
        budget = movie.budget,
        genres = emptyList(), //TODO movie.genres mapper
        homepage = movie.homepage,
        id = movie.id,
        imdbId = movie.imdbId,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        popularity = movie.popularity,
        posterPath = movie.posterPath,
        productionCompanies = emptyList(), //TODO list mapper
        productionCountries = emptyList(), //TODO list mapper
        releaseDate = movie.releaseDate,
        revenue = movie.revenue,
        runtime = movie.runtime,
        spokenLanguages = emptyList(), //TODO movie.spokenLanguages
        status = movie.status,
        tagline = movie.tagline,
        title = movie.title,
        video = movie.video,
        voteAverage = movie.voteAverage,
        voteCount = movie.voteCount)
}
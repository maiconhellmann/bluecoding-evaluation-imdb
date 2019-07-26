package com.hellmann.bluecoding.data.remote.mapper

import com.hellmann.bluecoding.data.remote.model.GenrePayload
import com.hellmann.bluecoding.data.remote.model.MovieListPayload
import com.hellmann.bluecoding.data.remote.model.MoviePayload
import com.hellmann.bluecoding.domain.entity.Genre
import com.hellmann.bluecoding.domain.entity.Movie

object MoviePayloadMapper {

    fun map(payloadList: MovieListPayload)= payloadList.results.map { payload-> map(payload) }

    fun map(payload: MoviePayload) = Movie(
        adult = payload.adult,
        backdropPath = payload.backdropPath,
        belongsToCollection = payload.belongsToCollection,
        budget = payload.budget,
        genres = payload.genres?.joinToString { it.name } ?: "",
        homepage = payload.homepage,
        id = payload.id,
        imdbId = payload.imdbId,
        originalLanguage = payload.originalLanguage,
        originalTitle = payload.originalTitle,
        overview = payload.overview,
        popularity = payload.popularity,
        posterPath = payload.posterPath,
        productionCompanies = emptyList(), //TODO list mapper
        productionCountries = emptyList(), //TODO list mapper
        releaseDate = payload.releaseDate,
        revenue = payload.revenue,
        runtime = payload.runtime,
        spokenLanguages = emptyList(), //TODO payload.spokenLanguages
        status = payload.status,
        tagline = payload.tagline,
        title = payload.title,
        video = payload.video,
        voteAverage = payload.voteAverage,
        voteCount = payload.voteCount)
}

object GenrePayloadMapper {
    fun map (payload: GenrePayload): Genre {
        return Genre(payload.id, payload.name)
    }
}
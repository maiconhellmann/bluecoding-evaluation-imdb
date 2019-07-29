package com.hellmann.bluecoding.data

import com.google.gson.GsonBuilder
import com.hellmann.bluecoding.data.remote.model.MoviePayload

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
object MoviePayloadMock {
    val moviePlayloadMock = lazy {
        GsonBuilder().create().fromJson(DEFAULT_MOVIE_PAYLOAD, MoviePayload::class.java)
    }
    private const val DEFAULT_MOVIE_PAYLOAD = """
{
    "vote_count": 7,
    "id": 613473,
    "video": false,
    "vote_average": 5.5,
    "title": "Burglary",
    "popularity": 614.73,
    "poster_path": "/xztkk3qwvjTfKArjTkhWUCuadFY.jpg",
    "original_language": "en",
    "original_title": "Burglary",
    "genre_ids": [
        27,
        53,
        9648
    ],
    "backdrop_path": null,
    "adult": false,
    "overview": "While playing videogames Mike spots a mysterious masked man outside his building.",
    "release_date": "2019-07-19"
}          
        """
}
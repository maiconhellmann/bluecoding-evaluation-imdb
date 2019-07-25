package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 25/07/2019
 * 
 * (c) 2019 
 */
class MovieListPayload(
    val page: Long, //
    @SerializedName("total_results") //
    val totalResults: Long, //
    @SerializedName("total_pages") //
    val totalPages: Long, val results: List<MoviePayload> //
)
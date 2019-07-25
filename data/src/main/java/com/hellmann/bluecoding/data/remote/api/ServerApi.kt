package com.hellmann.bluecoding.data.remote.api

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.data.remote.model.MovieListPayload
import io.reactivex.Single
import retrofit2.http.GET

interface ServerApi {

    @GET("${BuildConfig.API_VERSION}/movie/popular?api_key=${BuildConfig.API_KEY}")
    fun fetchArticles(): Single<MovieListPayload>
}
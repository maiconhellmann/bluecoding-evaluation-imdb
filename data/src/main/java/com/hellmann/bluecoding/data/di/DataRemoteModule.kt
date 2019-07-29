package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.data.remote.api.AuthenticationServerApi
import com.hellmann.bluecoding.data.remote.api.MovieServerApi
import com.hellmann.bluecoding.data.remote.source.authentication.AuthenticationRemoteDataSource
import com.hellmann.bluecoding.data.remote.source.authentication.AuthenticationRemoteDataSourceImpl
import com.hellmann.bluecoding.data.remote.source.movie.MovieRemoteDataSource
import com.hellmann.bluecoding.data.remote.source.movie.MovieRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    //OkHttp
    factory { providesOkHttpClient() }

    //MovieServerApi
    single {
        createWebService<MovieServerApi>(
            okHttpClient = get(), url = BuildConfig.BASE_URL)
    }
    //AuthenticationServerApi
    single {
        createWebService<AuthenticationServerApi>(okHttpClient = get(), url = BuildConfig.BASE_URL)
    }
    //MovieRemoteDataSource
    factory<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(
            movieApi = get())
    }
    //AuthenticationRemoteDataSource
    factory <AuthenticationRemoteDataSource>{
        AuthenticationRemoteDataSourceImpl(api = get())
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient, url: String
): T {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(url).client(okHttpClient)
        .build().create(T::class.java)
}
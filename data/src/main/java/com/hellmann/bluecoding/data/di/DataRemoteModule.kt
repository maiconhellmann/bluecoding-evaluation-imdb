package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }
    single {
        createWebService<ServerApi>(
            okHttpClient = get(), url = BuildConfig.BASE_URL)
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(articleApi = get()) }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient, url: String
): T {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(url).client(okHttpClient)
        .build().create(T::class.java)
}
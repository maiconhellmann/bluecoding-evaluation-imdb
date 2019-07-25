package com.hellmann.bluecoding.data

import com.hellmann.bluecoding.data.local.source.ArticleCacheDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.domain.entity.Article
import com.hellmann.bluecoding.domain.repository.MovieRepository
import io.reactivex.Single

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 25/05/2019
 * 
 * (c) 2019 
 */class MovieRepositoryImpl(
    private val cacheDataSource: ArticleCacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getMovies(forceUpdate: Boolean): Single<List<Article>> {
        return if (forceUpdate) {
            getArticlesRemote(forceUpdate)
        } else {
            cacheDataSource.getArticles().flatMap { articleList ->
                when {
                    articleList.isEmpty() -> getArticlesRemote(false)
                    else -> Single.just(articleList)
                }
            }
        }
    }

    private fun getArticlesRemote(forceUpdate: Boolean): Single<List<Article>> {
        return remoteDataSource.getMovies().flatMap { articleList ->
            if (forceUpdate) {
                cacheDataSource.updateData(articleList)
            } else {
                cacheDataSource.insertData(articleList)
            }
            Single.just(articleList)
        }
    }
}
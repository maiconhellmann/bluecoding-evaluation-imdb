package com.hellmann.bluecoding.domain.repository

import com.hellmann.bluecoding.domain.entity.Article
import io.reactivex.Single

interface ArticleRepository {
    fun getArticles(forceUpdate: Boolean): Single<List<Article>>
}
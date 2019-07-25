package com.hellmann.bluecoding.data.remote.source

import com.hellmann.bluecoding.domain.entity.Article
import io.reactivex.Single

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 25/05/2019
 * 
 * (c) 2019 
 */interface RemoteDataSource {
    fun getArticles(): Single<List<Article>>
}
package com.hellmann.bluecoding.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hellmann.bluecoding.data.local.model.ArticleCache
import io.reactivex.Single

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 25/05/2019
 * Data Access object
 *
 * (c) 2019
 */
@Dao
interface ArticleDao {
    @Query("SELECT * FROM ArticleCache")
    fun getAll(): Single<List<ArticleCache>>

    @Query("DELETE FROM ArticleCache")
    fun deleteAll()

    @Insert
    fun insertAll(list: List<ArticleCache>)

    @Transaction
    fun updateDate(list: List<ArticleCache>) {
        deleteAll()
        insertAll(list)
    }
}
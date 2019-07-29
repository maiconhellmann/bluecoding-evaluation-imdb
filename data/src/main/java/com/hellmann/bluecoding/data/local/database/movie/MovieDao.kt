package com.hellmann.bluecoding.data.local.database.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hellmann.bluecoding.data.local.model.MovieCache
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieCache")
    fun getAll(): Single<List<MovieCache>>

    @Query("DELETE FROM MovieCache")
    fun deleteAll()

    @Insert
    fun insertAll(list: List<MovieCache>)

    @Transaction
    fun updateDate(list: List<MovieCache>) {
        deleteAll()
        insertAll(list)
    }

    @Query("SELECT * FROM MovieCache WHERE id = :movieId")
    fun getById(movieId: Int): Single<MovieCache>
}
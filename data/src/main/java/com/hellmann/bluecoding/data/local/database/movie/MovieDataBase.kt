package com.hellmann.bluecoding.data.local.database.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellmann.bluecoding.data.local.model.MovieCache

@Database(version = 8, entities = [MovieCache::class], exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        fun createDatabase(context: Context): MovieDao {
            return Room.databaseBuilder(context, MovieDataBase::class.java, "Movies.db")
                .fallbackToDestructiveMigration().build().movieDao()
        }

        fun createDatabaseInMemory(context: Context): MovieDao {
            return Room.inMemoryDatabaseBuilder(context, MovieDataBase::class.java)
                .fallbackToDestructiveMigration().build().movieDao()
        }
    }
}
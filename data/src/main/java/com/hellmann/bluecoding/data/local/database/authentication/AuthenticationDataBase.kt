package com.hellmann.bluecoding.data.local.database.authentication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellmann.bluecoding.data.local.model.AuthenticationCache

@Database(version = 1, entities = [AuthenticationCache::class])
abstract class AuthenticationDataBase : RoomDatabase() {
    abstract fun dao(): AuthenticationDao

    companion object {
        fun createDatabase(context: Context): AuthenticationDao {
            return Room.databaseBuilder(context, AuthenticationDataBase::class.java, "Authentication.db")
                .fallbackToDestructiveMigration().build().dao()
        }

        fun createDatabaseInMemory(context: Context): AuthenticationDao {
            return Room.inMemoryDatabaseBuilder(context, AuthenticationDataBase::class.java)
                .fallbackToDestructiveMigration().build().dao()
        }
    }
}
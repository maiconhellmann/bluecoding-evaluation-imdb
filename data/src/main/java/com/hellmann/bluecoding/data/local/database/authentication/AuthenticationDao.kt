package com.hellmann.bluecoding.data.local.database.authentication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019
 */
@Dao
interface AuthenticationDao {
    @Query("SELECT * FROM AuthenticationCache limit 1")
    fun getGuestUserSession(): Single<Authentication>

    @Query("DELETE FROM AuthenticationCache")
    fun deleteAll()

    @Insert
    fun insert(authentication: Authentication)

    @Transaction
    fun updateGuestUserSession(authentication: Authentication): Authentication {
        deleteAll()
        insert(authentication)

        return authentication
    }
}
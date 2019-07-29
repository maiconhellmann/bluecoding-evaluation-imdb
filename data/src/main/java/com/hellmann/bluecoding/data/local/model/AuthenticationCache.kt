package com.hellmann.bluecoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
@Entity
class AuthenticationCache (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val success: Boolean,
    val guestSessionId: String,
    val expirestAt: String? = null
)
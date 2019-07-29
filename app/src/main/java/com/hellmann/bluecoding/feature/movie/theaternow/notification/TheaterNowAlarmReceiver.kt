package com.hellmann.bluecoding.feature.movie.theaternow.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
class TheaterNowAlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val controller: TheaterNowNotificationController by inject()

    /**
     * Every time the alarm manager send a broadcast, this receiver captures and verify new movies in theater.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        controller.verifyMoviesInTheater()
    }
}
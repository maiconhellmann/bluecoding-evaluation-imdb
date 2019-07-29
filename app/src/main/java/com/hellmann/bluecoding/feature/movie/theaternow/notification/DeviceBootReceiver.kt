package com.hellmann.bluecoding.feature.movie.theaternow.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hellmann.bluecoding.data.di.dataModules
import com.hellmann.bluecoding.di.schedulerModule
import com.hellmann.bluecoding.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */class DeviceBootReceiver : BroadcastReceiver(), KoinComponent {

    /**
     * Called when the device is rebooted and register the Alarm manager to send a push notification per day
     */
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            //start Koin because the app is not started yet
            startKoin {
                androidContext(context)
                modules(dataModules + domainModule + schedulerModule)
            }
            val controller: TheaterNowNotificationController by inject()
            controller.startAlarmManager()
        }
    }
}
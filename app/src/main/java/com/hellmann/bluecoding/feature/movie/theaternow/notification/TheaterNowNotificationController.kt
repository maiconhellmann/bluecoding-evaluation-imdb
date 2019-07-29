package com.hellmann.bluecoding.feature.movie.theaternow.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.usecase.GetMoviesUseCase
import com.hellmann.bluecoding.feature.main.MainActivity
import com.hellmann.bluecoding.feature.viewmodel.StateMachineSingle
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
class TheaterNowNotificationController(
    private val useCase: GetMoviesUseCase,
    private val uiScheduler: Scheduler,
    private val context: Context
) {

    /**
     * Verifies if there are movies in theater right now and send a push notification to the user.
     */
    fun verifyMoviesInTheater() {

        useCase.getMoviesInTheater().subscribeOn(uiScheduler).compose(StateMachineSingle())
            .subscribeBy(onSuccess = { state ->
                when (state) {
                    is ViewState.Success -> {
                        sendNotification(state.data)
                    }
                }
            })
    }

    /**
     * Send a notification to the user with the first 3 movies in the list
     */
    private fun sendNotification(movieList: List<Movie>) {
        if (movieList.isEmpty() || movieList.size < 3) return

        val notificationContent = with(movieList.take(3).map { it.title }) {
            context.getString(
                R.string.movies_theater_now_notification_description,
                this[0],
                this[1],
                this[2])
        }

        createNotificationChannel()

        //Deeplink for Movies in Theater
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.theater_now_dest)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_local_movies_white_24dp)
            .setContentTitle(context.getString(R.string.movies_theater_now_notification_title))
            .setContentText(notificationContent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationContent))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    /**
     * Setup and start Alarm Manager
     */
    fun startAlarmManager() {
        val manager = context.getSystemService(ALARM_SERVICE) as AlarmManager

        /* Set the alarm to start at 08:00 AM */
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar. DATE, 1)//starts tomorrow
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        /* Repeating on every 24 hours interval */
        manager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, getPendingState(context))
    }

    /**
     * Cancel Alarm Manager
     */
    fun cancellAlarmManager() {
        val manager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        manager.cancel(getPendingState(context))
    }

    /**
     * Create a notification channel to be able to show notification for android O
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1001
        const val CHANNEL_ID = "MOVIES_IN_THEATER"

        fun getPendingState(context: Context): PendingIntent {
            val alarmIntent = Intent(context, TheaterNowAlarmReceiver::class.java)
            return PendingIntent.getBroadcast(context, 0, alarmIntent, 0)
        }
    }
}
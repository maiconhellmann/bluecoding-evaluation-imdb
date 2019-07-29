package com.hellmann.bluecoding.di

import com.hellmann.bluecoding.feature.movie.theaternow.notification.TheaterNowNotificationController
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */

val schedulerModule = module {
    single {
        TheaterNowNotificationController(
            useCase = get(),
            uiScheduler = AndroidSchedulers.mainThread(),
            context = androidContext())
    }
}
package com.hellmann.bluecoding.di

import com.hellmann.bluecoding.feature.list.ArticleViewModel
import com.hellmann.bluecoding.feature.list.ArticlesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { ArticlesAdapter() }

    viewModel { ArticleViewModel(
        useCase = get(),
        uiScheduler = AndroidSchedulers.mainThread()
    ) }
}
package com.fero.submission1made.di

import com.fero.submission1made.core.domain.usecase.MealInteractor
import com.fero.submission1made.core.domain.usecase.MealUseCase
import com.fero.submission1made.detail.DetailMealViewModel
import com.fero.submission1made.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMealViewModel(get()) }
}


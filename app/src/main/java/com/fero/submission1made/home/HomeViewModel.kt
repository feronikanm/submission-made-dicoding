package com.fero.submission1made.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fero.submission1made.core.domain.usecase.MealUseCase

class HomeViewModel(mealUseCase: MealUseCase) : ViewModel() {
    val meal = mealUseCase.getAllMeal().asLiveData()
}
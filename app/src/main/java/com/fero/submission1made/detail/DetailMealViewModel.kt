package com.fero.submission1made.detail

import androidx.lifecycle.ViewModel
import com.fero.submission1made.core.domain.model.Meal
import com.fero.submission1made.core.domain.usecase.MealUseCase

class DetailMealViewModel(private val mealUseCase: MealUseCase) : ViewModel() {

    fun setFavoriteMeal(meal: Meal, newStatus:Boolean) = mealUseCase.setFavoriteMeal(meal, newStatus)
}
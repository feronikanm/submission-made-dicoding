package com.fero.submission1made.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fero.submission1made.core.domain.usecase.MealUseCase

class FavoriteViewModel(mealUseCase: MealUseCase) : ViewModel() {

    val favoriteMeal = mealUseCase.getFavoriteMeal().asLiveData()

}
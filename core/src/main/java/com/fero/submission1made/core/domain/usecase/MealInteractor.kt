package com.fero.submission1made.core.domain.usecase

import com.fero.submission1made.core.domain.model.Meal
import com.fero.submission1made.core.domain.repository.IMealRepository

class MealInteractor (private val mealRepository: IMealRepository): MealUseCase {

    override fun getAllMeal() = mealRepository.getAllMeal()

    override fun getFavoriteMeal() = mealRepository.getFavoriteMeal()

    override fun setFavoriteMeal(meal: Meal, state: Boolean) = mealRepository.setFavoriteMeal(meal, state)

}
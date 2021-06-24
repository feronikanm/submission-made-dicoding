package com.fero.submission1made.core.domain.repository

import com.fero.submission1made.core.data.Resource
import com.fero.submission1made.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface IMealRepository {

    fun getAllMeal(): Flow<Resource<List<Meal>>>

    fun getFavoriteMeal(): Flow<List<Meal>>

    fun setFavoriteMeal(meal: Meal, state: Boolean)
}
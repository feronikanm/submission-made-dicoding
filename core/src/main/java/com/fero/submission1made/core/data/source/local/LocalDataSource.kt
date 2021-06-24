package com.fero.submission1made.core.data.source.local

import com.fero.submission1made.core.data.source.local.entity.MealEntity
import com.fero.submission1made.core.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao: MealDao) {


    fun getAllMeal(): Flow<List<MealEntity>> = mealDao.getAllMeal()

    fun getFavoriteMeal(): Flow<List<MealEntity>> = mealDao.getFavoriteMeal()

    suspend fun insertMeal(mealList: List<MealEntity>) = mealDao.insertMeal(mealList)

    fun setFavoriteMeal(meal: MealEntity, newState: Boolean) {
        meal.isFavorite = newState
        mealDao.updateFavoriteMeal(meal)
    }
}
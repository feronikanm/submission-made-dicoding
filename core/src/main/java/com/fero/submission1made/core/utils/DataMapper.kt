package com.fero.submission1made.core.utils

import com.fero.submission1made.core.data.source.local.entity.MealEntity
import com.fero.submission1made.core.data.source.remote.response.MealResponse
import com.fero.submission1made.core.domain.model.Meal

object DataMapper {

    fun mapResponsesToEntities(input: List<MealResponse>): List<MealEntity> {
        val mealList = ArrayList<MealEntity>()
        input.map {
            val meal = MealEntity(
                mealId = it.id,
                name = it.name,
                image = it.image,
                description = it.description,
                isFavorite = false
            )
            mealList.add(meal)
        }
        return mealList
    }

    fun mapEntitiesToDomain(input: List<MealEntity>): List<Meal> =
        input.map {
            Meal(
                mealId = it.mealId,
                name = it.name,
                image = it.image,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Meal) = MealEntity(
            mealId = input.mealId,
            name = input.name,
            image = input.image,
            description = input.description,
            isFavorite = input.isFavorite
        )

}
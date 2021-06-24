package com.fero.submission1made.core.data.source.local.room

import androidx.room.*
import com.fero.submission1made.core.data.source.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meal")
    fun getAllMeal(): Flow<List<MealEntity>>

    @Query("SELECT * FROM meal where isFavorite = 1")
    fun getFavoriteMeal(): Flow<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: List<MealEntity>)

    @Update
    fun updateFavoriteMeal(meal: MealEntity)

}
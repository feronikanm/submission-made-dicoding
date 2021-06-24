package com.fero.submission1made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fero.submission1made.core.data.source.local.entity.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

}
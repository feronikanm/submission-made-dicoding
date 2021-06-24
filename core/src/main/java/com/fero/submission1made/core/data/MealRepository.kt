package com.fero.submission1made.core.data

import com.fero.submission1made.core.data.source.local.LocalDataSource
import com.fero.submission1made.core.data.source.remote.RemoteDataSource
import com.fero.submission1made.core.data.source.remote.network.ApiResponse
import com.fero.submission1made.core.data.source.remote.response.MealResponse
import com.fero.submission1made.core.domain.model.Meal
import com.fero.submission1made.core.domain.repository.IMealRepository
import com.fero.submission1made.core.utils.AppExecutors
import com.fero.submission1made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMealRepository {

    override fun getAllMeal(): Flow<Resource<List<Meal>>> =
        object : com.fero.submission1made.core.data.NetworkBoundResource<List<Meal>, List<MealResponse>>() {
            override fun loadFromDB(): Flow<List<Meal>> {
                return localDataSource.getAllMeal().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Meal>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MealResponse>>> =
                remoteDataSource.getAllMeal()

            override suspend fun saveCallResult(data: List<MealResponse>) {
                val mealList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMeal(mealList)
            }
        }.asFlow()


    override fun getFavoriteMeal(): Flow<List<Meal>> {
        return localDataSource.getFavoriteMeal().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMeal(meal: Meal, state: Boolean) {
        val mealEntity = DataMapper.mapDomainToEntity(meal)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMeal(mealEntity, state) }
    }
}
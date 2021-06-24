package com.fero.submission1made.core.data.source.remote

import android.util.Log
import com.fero.submission1made.core.data.source.remote.network.ApiResponse
import com.fero.submission1made.core.data.source.remote.network.ApiService
import com.fero.submission1made.core.data.source.remote.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMeal(): Flow<ApiResponse<List<MealResponse>>> {

        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.categories
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.categories))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}
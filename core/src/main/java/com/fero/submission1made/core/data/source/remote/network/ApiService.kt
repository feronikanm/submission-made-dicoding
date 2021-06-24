package com.fero.submission1made.core.data.source.remote.network

import com.fero.submission1made.core.data.source.remote.response.ListMealResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getList(): ListMealResponse
}
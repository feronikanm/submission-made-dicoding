package com.fero.submission1made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMealResponse (

    @field:SerializedName("categories")
    val categories: List<MealResponse>
)
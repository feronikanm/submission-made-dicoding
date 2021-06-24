package com.fero.submission1made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MealResponse (

    @field:SerializedName("idCategory")
    val id: String,

    @field:SerializedName("strCategory")
    val name: String,

    @field:SerializedName("strCategoryThumb")
    val image: String,

    @field:SerializedName("strCategoryDescription")
    val description: String

)
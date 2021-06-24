package com.fero.submission1made.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(

    val mealId: String,
    val name: String,
    val image: String,
    val description: String,
    val isFavorite: Boolean

) : Parcelable
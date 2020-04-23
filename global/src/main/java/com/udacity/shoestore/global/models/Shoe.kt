package com.udacity.shoestore.global.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Model representing a shoe
 */
@Parcelize
data class Shoe(
    var name: String = "",
    var size: Double? = 0.0,
    var company: String = "",
    var description: String = "",
    val images: MutableList<Bitmap> = mutableListOf()
) : Parcelable

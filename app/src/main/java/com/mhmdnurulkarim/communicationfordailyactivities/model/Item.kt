package com.mhmdnurulkarim.communicationfordailyactivities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val activitiesName: String,
    val photo: Int,
    val audio: Int
) : Parcelable

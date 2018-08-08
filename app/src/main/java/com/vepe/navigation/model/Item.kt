package com.vepe.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Item(val index: Int, val title: String, val value: Double): Parcelable
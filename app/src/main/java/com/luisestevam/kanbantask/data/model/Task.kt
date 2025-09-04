package com.luisestevam.kanbantask.data.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task (
    val id: String,
    val description: String
): Parcelable
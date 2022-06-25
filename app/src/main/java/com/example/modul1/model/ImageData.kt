package com.example.modul1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(
    val name: Int,
    val title: String,
    val date: String
): Parcelable
package com.example.modul1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CategoryAlbum(
    var category: String,
    var album: ArrayList<Album>
) : Parcelable

@Parcelize
data class Album(
    var title: String
) : Parcelable
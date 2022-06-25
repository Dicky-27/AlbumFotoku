package com.example.modul1.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "album")
@Parcelize
data class Album(
    @PrimaryKey(autoGenerate = false)
    var category: String,
    var titleAlbum: String
) : Parcelable
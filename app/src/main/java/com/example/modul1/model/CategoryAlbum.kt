package com.example.modul1.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category")
@Parcelize
data class CategoryAlbum(
    @PrimaryKey(autoGenerate = false)
    val category: String
) : Parcelable
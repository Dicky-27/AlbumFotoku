package com.example.modul1.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cart")
@Parcelize
data class Cart(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "date") var date: String = "",
    @ColumnInfo(name = "image") var image: Int = 0,
    @ColumnInfo(name = "count") var count: Int = 0,
    @ColumnInfo(name = "size") var size: String = "",
    @ColumnInfo(name = "price") var price: Int = 0
) : Parcelable


package com.example.modul1.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithAlbum(
    @Embedded val category: CategoryAlbum,
    @Relation(
        parentColumn = "category",
        entityColumn = "category"
    )
    val albums: List<Album>
)
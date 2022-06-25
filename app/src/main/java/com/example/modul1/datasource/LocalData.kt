package com.example.modul1.datasource

import android.app.Application
import com.example.modul1.model.ImageData
import com.example.modul1.R
import com.example.modul1.model.Album
import com.example.modul1.model.CategoryAlbum

class LocalData: Application() {
    companion object {
        val images: Array<ImageData> = arrayOf(
            ImageData(R.drawable.img_bromo, "Bromo", "28/02/2000"),
            ImageData(R.drawable.img_canada, "Canada", "01/01/1987"),
            ImageData(R.drawable.img_canada_river, "Canada River", "08/02/2003"),
            ImageData(R.drawable.img_europe, "Europe", "18/05/2009"),
            ImageData(R.drawable.img_german, "Germany", "12/08/2010"),
            ImageData(R.drawable.img_mountain, "Mountain", "15/09/2011"),
            ImageData(R.drawable.img_nature, "Nature","28/02/2000"),
            ImageData(R.drawable.img_newyork, "NewYork", "28/02/2000"),
            ImageData(R.drawable.img_river, "River","28/02/2000")
        )

        val category: List<CategoryAlbum> = listOf(
            CategoryAlbum("Test 1", arrayListOf(Album("Title1"), Album("Title 2"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3")))
        )
    }
}
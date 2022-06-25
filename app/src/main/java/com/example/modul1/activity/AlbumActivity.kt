package com.example.modul1.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul1.R
import com.example.modul1.adapter.AlbumAdapter
import com.example.modul1.base.BaseActivity
import com.example.modul1.databinding.ActivityAlbumBinding
import com.example.modul1.datasource.LocalData.Companion.category
import com.example.modul1.model.Album
import com.example.modul1.model.CategoryAlbum

class AlbumActivity : BaseActivity<ActivityAlbumBinding>() {
    private lateinit var albumAdapter: AlbumAdapter

    override val bindingInflater: (LayoutInflater) -> ActivityAlbumBinding
        get() = ActivityAlbumBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        configureRv()
    }

    private fun configureRv() {
        val recyclerView = binding.rvAlbum
        val data: List<CategoryAlbum> = arrayListOf(
            CategoryAlbum("Test 1", arrayListOf(Album("Title1"), Album("Title 2"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3"))),
            CategoryAlbum("Test 2", arrayListOf(Album("Title2"), Album("Title 3")))
        )

        albumAdapter = AlbumAdapter(data, View.OnClickListener {
            Log.d("test222", category.toString())
        })

        Log.d("test", category.toString())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AlbumActivity)
            adapter = albumAdapter
        }
    }

}
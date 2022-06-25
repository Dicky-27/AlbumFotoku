package com.example.modul1.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul1.R
import com.example.modul1.adapter.AlbumAdapter
import com.example.modul1.base.BaseActivity
import com.example.modul1.databinding.ActivityAlbumBinding
import com.example.modul1.db.CartRoomDatabase
import com.example.modul1.model.Album
import com.example.modul1.model.CategoryAlbum
import kotlinx.coroutines.launch

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
        val dao = CartRoomDatabase.getDatabase(this).getNoteDao()
        val category = listOf(
            CategoryAlbum("Nature"),
            CategoryAlbum("Building"),
            CategoryAlbum("Street")
        )

        val album = listOf(
            Album("Nature", "Gunung"),
            Album("Nature", "Sungai"),
            Album("Nature", "Hutan"),
            Album("Building", "Sekolah"),
            Album("Street", "Kantor"),
            Album( "Street", "Rumah"),
        )

        lifecycleScope.launch {
            category.forEach { dao.insertCategory(it) }
            album.forEach { dao.insertAlbum(it) }

            val categoryWithAlbum = dao.getCategory()

            Log.d("test222", categoryWithAlbum.toString())

            albumAdapter = AlbumAdapter(categoryWithAlbum, View.OnClickListener {
                Log.d("test222", category.toString())
            })

            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@AlbumActivity)
                adapter = albumAdapter
            }
        }
    }

}
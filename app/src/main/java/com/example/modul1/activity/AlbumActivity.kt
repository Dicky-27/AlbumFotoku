package com.example.modul1.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul1.R
import com.example.modul1.adapter.AlbumAdapter
import com.example.modul1.base.BaseActivity
import com.example.modul1.databinding.ActivityAlbumBinding
import com.example.modul1.db.CartRoomDatabase
import com.example.modul1.model.Album
import com.example.modul1.model.CategoryAlbum
import com.example.modul1.model.CategoryWithAlbum
import kotlinx.coroutines.launch

class AlbumActivity : BaseActivity<ActivityAlbumBinding>() {
    private lateinit var albumAdapter: AlbumAdapter

    override val bindingInflater: (LayoutInflater) -> ActivityAlbumBinding
        get() = ActivityAlbumBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val dao = CartRoomDatabase.getDatabase(this).getNoteDao()
        val categoryWithAlbum = dao.getCategory()
        Log.d("test222", categoryWithAlbum.toString())
        albumAdapter = AlbumAdapter(categoryWithAlbum, dao, object : AlbumAdapter.OnAdapterListener{
            override fun onItemClick(position: Int) {
                Log.d("testfff", position.toString())
                val dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                albumAdapter.deleteCategory(position)
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }

                val builder = android.app.AlertDialog.Builder(this@AlbumActivity)
                builder.setMessage("Jika kategory dihapus maka semua album didalam kategori akan dihapus, \n ada yakin untuk dilanjutkan?").setPositiveButton("Ya", dialogClickListener)
                    .setNegativeButton("Tidak", dialogClickListener).show()
            }

        })

        val rv: RecyclerView = this.findViewById(R.id.rv_album_ku)
        rv.adapter = albumAdapter
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = lm
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        albumAdapter.notifyDataSetChanged()
    }

}
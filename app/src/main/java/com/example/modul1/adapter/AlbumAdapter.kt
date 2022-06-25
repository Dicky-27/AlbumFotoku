package com.example.modul1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul1.R
import com.example.modul1.model.CategoryAlbum

class AlbumAdapter(
    private val mainHeadingData: List<CategoryAlbum>,
    val clickListener: View.OnClickListener
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        Log.d("test2", mainHeadingData.toString())
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategory.text = mainHeadingData[position].category

        val childLayoutManager = LinearLayoutManager(holder.itemView.findViewById<RecyclerView>(R.id.rvSub).context, RecyclerView.VERTICAL, false)
        Log.d("test3", mainHeadingData.toString())
        holder.itemView.findViewById<RecyclerView>(R.id.rvSub).apply {
            layoutManager = childLayoutManager
            adapter = SubAdapter(mainHeadingData.get(position))
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return mainHeadingData.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory = view.findViewById<TextView>(R.id.tvCategory)
    }
}
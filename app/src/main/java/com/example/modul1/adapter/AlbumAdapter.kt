package com.example.modul1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul1.R
import com.example.modul1.db.CartDao
import com.example.modul1.model.CategoryAlbum
import com.example.modul1.model.CategoryWithAlbum

class AlbumAdapter(
    private val mainHeadingData: List<CategoryWithAlbum>,
    private val cartDao: CartDao,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        Log.d("test2", mainHeadingData.toString())
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = position
        holder.tvCategory.text = mainHeadingData[position].category.category

        val childLayoutManager = LinearLayoutManager(holder.itemView.findViewById<RecyclerView>(R.id.rvSub).context, RecyclerView.VERTICAL, false)
        holder.itemView.findViewById<RecyclerView>(R.id.rvSub).apply {
            layoutManager = childLayoutManager
            adapter = SubAdapter(mainHeadingData[position])
            setRecycledViewPool(viewPool)
        }

        holder.tvCategory.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    interface OnAdapterListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return mainHeadingData.size
    }

    fun deleteCategory(position: Int) {
        val itemCat = mainHeadingData[position].category
        val currentSize = mainHeadingData.count()
        (mainHeadingData as? MutableList)?.removeAt(0)
        notifyItemRangeRemoved(0, currentSize)
        cartDao.deleteCategory(itemCat)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory = view.findViewById<TextView>(R.id.tvCategory)
    }
}
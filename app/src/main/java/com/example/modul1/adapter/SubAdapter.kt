package com.example.modul1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul1.R
import com.example.modul1.model.Album
import com.example.modul1.model.CategoryAlbum

class SubAdapter(
    val subHeadingData: List<Album>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return subHeadingData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSubType.text = subHeadingData.get(position).titleAlbum
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_subtitle, parent, false))
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvSubType = view.findViewById<TextView>(R.id.tvSubtitle)
}

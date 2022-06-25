package com.example.modul1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul1.R
import com.example.modul1.db.CartDao
import com.example.modul1.model.Cart


class CartAdapter(
    private val dataSet: List<Cart>,
    private val cartDao: CartDao,
    val listener: OnAdapterListener
    ) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleItem: TextView
        val imageItem: ImageView
        val size: TextView
        val count: TextView
        val subTotal: TextView
        val buttonMin: Button
        val buttonPlus: Button

        init {
            titleItem = view.findViewById(R.id.textView)
            size = view.findViewById(R.id.desc)
            imageItem = view.findViewById(R.id.imageView)
            count = view.findViewById(R.id.counterText)
            buttonMin = view.findViewById(R.id.minusButton)
            buttonPlus = view.findViewById(R.id.addButton)
            subTotal = view.findViewById(R.id.subTotal)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_cart, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageItem.setImageResource(dataSet[position].image)
        viewHolder.titleItem.text = dataSet[position].title
        viewHolder.count.text = dataSet[position].count.toString()
        viewHolder.size.text = dataSet[position].size
        viewHolder.subTotal.text = "Rp. " + dataSet[position].price.toString()

        var count = 1
        var price = dataSet[position].price
        var subTot = dataSet[position].price
        viewHolder.buttonMin.setOnClickListener {
            if(count == 1) {
                deleteCart(position)
                subTot = 0
                listener.onItemClick(subTot)
            } else {
                count = count - 1
                subTot = subTot - (count * price)
                viewHolder.count.text = count.toString()
                viewHolder.subTotal.text = "Rp. $subTot"
                listener.onItemClick(subTot)
            }
        }
        viewHolder.buttonPlus.setOnClickListener {
            count = count + 1
            subTot = count * price
            viewHolder.count.text = count.toString()
            viewHolder.subTotal.text = "Rp. $subTot"
            listener.onItemClick(subTot)
        }
    }

    override fun getItemCount() = dataSet.size

    private fun deleteCart(position: Int) {
        val item = dataSet[position]
        (dataSet as MutableList).remove(item)
        notifyItemChanged(position)
        cartDao.delete(item)
    }

    interface OnAdapterListener {
        fun onItemClick(price: Int)
    }

    fun clear() {
        val size: Int = dataSet.size
        (dataSet as MutableList).clear()
        notifyItemRangeRemoved(0, size)
        cartDao.nukeTable()
    }
}

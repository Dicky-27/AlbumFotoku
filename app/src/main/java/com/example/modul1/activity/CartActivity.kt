package com.example.modul1.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul1.adapter.CartAdapter
import com.example.modul1.base.BaseActivity
import com.example.modul1.databinding.ActivityCartBinding
import com.example.modul1.db.CartDao
import com.example.modul1.db.CartRoomDatabase
import com.example.modul1.model.Cart


class CartActivity : BaseActivity<ActivityCartBinding>() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var database: CartRoomDatabase
    private lateinit var dao: CartDao
    private lateinit var actionBar: ActionBar

    var total = 0
    override val bindingInflater: (LayoutInflater) -> ActivityCartBinding
        get() = ActivityCartBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureRecyclerView()
        actionBar = supportActionBar!!
        actionBar.title = "Keranjang"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        database = CartRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()
        cartAdapter.notifyDataSetChanged()
    }

    private fun configureRecyclerView() {
        binding.tvPrice.text = "Total: Rp. 8000"
        val database = CartRoomDatabase.getDatabase(applicationContext)
        val dao = database.getNoteDao()
        var listItems = listOf<Cart>()
        listItems = dao.getAll()

        val recyclerview = binding.cartRecycler
        cartAdapter = CartAdapter(listItems, dao, object : CartAdapter.OnAdapterListener {
            override fun onItemClick(price: Int) {
                total = total + (cartAdapter.itemCount * price)
                binding.tvPrice.text = "Total: Rp. " + total.toString()
            }
        })

        binding.btnDeleteAll.setOnClickListener {
            val dialogClickListener =
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            cartAdapter.clear()
                            binding.tvPrice.text = "Total: Rp. 0"
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }

            val builder = android.app.AlertDialog.Builder(this)
            builder.setMessage("Anda yakin mau mengosongkan keranjang belanja?").setPositiveButton("Ya", dialogClickListener)
                .setNegativeButton("Tidak", dialogClickListener).show()
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }
}
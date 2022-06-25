package com.example.modul1.activity

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.MenuItemCompat
import com.example.modul1.R
import com.example.modul1.base.BaseActivity
import com.example.modul1.databinding.ActivityMainBinding
import com.example.modul1.databinding.ItemBinding
import com.example.modul1.datasource.LocalData
import com.example.modul1.db.CartDao
import com.example.modul1.db.CartRoomDatabase
import com.example.modul1.model.Cart
import java.lang.String
import kotlin.Boolean


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var cart: Cart
    private lateinit var database: CartRoomDatabase
    private lateinit var dao: CartDao
    private lateinit var actionBar: androidx.appcompat.app.ActionBar

    var count = 0
    var textCartItemCount: TextView? = null
    var mCartItemCount = 0

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = CartRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()
        actionBar = supportActionBar!!
        actionBar.title = "Beranda"
        configureView()
    }

    @SuppressLint("SetTextI18n")
    private fun configureView() {
        val gallery = findViewById<LinearLayout>(R.id.gallery)
        val inflater = LayoutInflater.from(this)
        var listItems = listOf<Cart>()
        listItems = dao.getAll()
        count = listItems.count()
        for (element in LocalData.images) {
            var isHidden = true
            val view = ItemBinding.inflate(LayoutInflater.from(this), gallery, false)
            view.text.text = element.title
            view.dateText.text = "Take on "+element.date
            view.image.setImageResource(element.name)
            view.groupToggle.setVisible(isHidden)
            view.image.setOnLongClickListener {
                Toast.makeText(
                    this,
                    element.title+", "+element.date,
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            view.cetak.setOnClickListener {
                isHidden = !isHidden
                view.groupToggle.setVisible(isHidden)
            }

            var newCart = Cart(element.name, "", "", 0, 0,"3R", 2000)
            view.groupToggle.addOnButtonCheckedListener { group, checkedId, isChecked ->
                if (isChecked) {
                    count = 0
                    when (checkedId) {
                        R.id.first ->  {
                            count = count + 1
                            newCart = Cart(element.name, element.title, element.date, element.name, count,"3R", 2000)
                            saveCart(newCart)
                        }
                        R.id.second -> {
                            count = count + 1
                            newCart = Cart(element.name, element.title, element.date, element.name, count,"4R", 4000)
                            saveCart(newCart)
                        }
                        R.id.third -> {
                            count = count + 1
                            newCart = Cart(element.name, element.title, element.date, element.name, count,"8R", 8000)
                            saveCart(newCart)
                        }
                        R.id.fourt -> {
                            count = count + 1
                            newCart = Cart(element.name, element.title, element.date, element.name, count,"10R", 10000)
                            saveCart(newCart)
                        }
                    }
                    mCartItemCount = mCartItemCount + count
                    setupBadge()
                } else {
                    mCartItemCount = mCartItemCount - 1
                    deleteCart(newCart)
                    setupBadge()
                }
            }
            gallery.addView(view.root)
        }

        binding.btnAdd.setOnClickListener {
            startActivity(
                Intent(this, AlbumActivity::class.java)
            )
        }
    }

    private fun saveCart(cart: Cart){
        if (dao.getById(cart.id).isEmpty()){
            dao.insert(cart)
        }
        else{
            dao.update(cart)
        }
    }

    private fun deleteCart(cart: Cart) {
        dao.delete(cart)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        val item = menu!!.findItem(R.id.cart_button)
        MenuItemCompat.setActionView(item, R.layout.badge_cart_button)
        val notifCount = MenuItemCompat.getActionView(item) as RelativeLayout
        val textCount = notifCount.findViewById<View>(R.id.actionbar_notifcation_textview) as TextView
        val actionView = notifCount.findViewById<View>(R.id.cart_image) as ImageView
        textCartItemCount = textCount
        setupBadge()

        actionView.setOnClickListener(View.OnClickListener { onOptionsItemSelected(item) })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart_button -> {
                startActivity(
                    Intent(this, CartActivity::class.java)
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun View.setVisible(isHidden: Boolean) {
        visibility = if (isHidden) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun setupBadge() {
        if (textCartItemCount != null) {
            if (mCartItemCount === 0) {
                if (textCartItemCount?.getVisibility() !== View.GONE) {
                    textCartItemCount?.setVisibility(View.GONE)
                }
            } else {
                textCartItemCount?.setText(String.valueOf(Math.min(mCartItemCount, 99)))
                if (textCartItemCount?.getVisibility() !== View.VISIBLE) {
                    textCartItemCount?.setVisibility(View.VISIBLE)
                }
            }
        }
    }
}
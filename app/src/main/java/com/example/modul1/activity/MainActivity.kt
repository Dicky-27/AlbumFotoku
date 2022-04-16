package com.example.modul1.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.modul1.R
import com.example.modul1.datasource.LocalData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureView()
    }

    @SuppressLint("SetTextI18n")
    private fun configureView() {
        val gallery = findViewById<LinearLayout>(R.id.gallery)
        val inflater = LayoutInflater.from(this)
        for (element in LocalData.images) {
            var isHidden = true
            val view = inflater.inflate(R.layout.item, gallery, false)
            val title = view.findViewById<TextView>(R.id.text)
            val dateText = view.findViewById<TextView>(R.id.date_text)
            val image = view.findViewById<ImageView>(R.id.image)
            val sizeView = view.findViewById<LinearLayout>(R.id.linearHorizontal)
            val cetakButton = view.findViewById<Button>(R.id.cetak)
            title.text = element.title
            dateText.text = "Take on "+element.date
            image.setImageResource(element.name)
            sizeView.setVisible(isHidden)
            view.setOnLongClickListener {
                Toast.makeText(
                    this,
                    element.title+", "+element.date,
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            cetakButton.setOnClickListener {
                isHidden = !isHidden
                sizeView.setVisible(isHidden)
            }
            gallery.addView(view)
        }
    }

    private fun View.setVisible(isHidden: Boolean) {
        visibility = if (isHidden) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
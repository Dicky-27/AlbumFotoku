package com.example.modul1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modul1.model.Album
import com.example.modul1.model.Cart
import com.example.modul1.model.CategoryAlbum


@Database(
    entities = [
        Cart::class,
        CategoryAlbum::class,
        Album::class
               ],
    version = 1, exportSchema = false)
abstract class CartRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: CartRoomDatabase? = null

        fun getDatabase(context: Context): CartRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartRoomDatabase::class.java,
                    "cart_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getNoteDao() : CartDao
}
package com.example.modul1.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.modul1.model.Album
import com.example.modul1.model.Cart
import com.example.modul1.model.CategoryAlbum
import com.example.modul1.model.CategoryWithAlbum

@Dao
interface CartDao {

    @Insert
    fun insert(cart: Cart)

    @Update
    fun update(cart: Cart)

    @Delete
    fun delete(cart: Cart)

    @Query("SELECT * FROM cart")
    fun getAll() : List<Cart>

    @Query("SELECT * FROM cart WHERE id = :id")
    fun getById(id: Int) : List<Cart>

    @Query("DELETE FROM cart")
    fun nukeTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryAlbum)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Transaction
    @Query("SELECT * FROM category")
    fun getCategory(): List<CategoryWithAlbum>
}


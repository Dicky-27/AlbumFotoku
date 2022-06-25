package com.example.modul1.db

import androidx.room.*
import com.example.modul1.model.Cart

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
}
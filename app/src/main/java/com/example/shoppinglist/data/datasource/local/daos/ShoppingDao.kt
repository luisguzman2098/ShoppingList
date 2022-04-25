package com.example.shoppinglist.data.datasource.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ShoppingEntity)

    @Update
    suspend fun update(entity: ShoppingEntity)

    @Delete
    suspend fun delete(entity: ShoppingEntity)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingEntity>>
}
package com.example.shoppinglist.data.datasource.shopping

import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingDataSource<T> {

    suspend fun getAllShopping(): Flow<List<T>>
    suspend fun updateShoppingItem(shoppingItem: T) : Boolean
    suspend fun insertShoppingItem(shoppingItem: T): Boolean
    suspend fun deleteShoppingItem(shoppingItem: T): Boolean
}
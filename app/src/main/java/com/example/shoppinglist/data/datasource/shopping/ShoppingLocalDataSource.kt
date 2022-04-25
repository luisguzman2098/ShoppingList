package com.example.shoppinglist.data.datasource.shopping

import androidx.lifecycle.asFlow
import com.example.shoppinglist.data.datasource.local.daos.ShoppingDao
import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingLocalDataSource @Inject constructor(
    private val shoppingDao: ShoppingDao
) : ShoppingDataSource<ShoppingEntity> {
    override suspend fun getAllShopping(): Flow<List<ShoppingEntity>> {
        return shoppingDao.getAllShoppingItems().asFlow()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingEntity): Boolean {
        shoppingDao.insert(shoppingItem)
        return true
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingEntity): Boolean {
        shoppingDao.update(shoppingItem)
        return true
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingEntity): Boolean {
        shoppingDao.delete(shoppingItem)
        return true
    }
}
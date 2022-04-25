package com.example.shoppinglist.data.datasource.repositories

import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IShoppingRepository {

    fun getShoppingList(): Flow<Resource<List<ShoppingModel>>>
    fun insertShopping(shopping: ShoppingModel) : Flow<Resource<Boolean>>
    fun updateShopping(shopping: ShoppingModel) : Flow<Resource<Boolean>>
    fun deleteShopping(shopping: ShoppingModel) : Flow<Resource<Boolean>>
}
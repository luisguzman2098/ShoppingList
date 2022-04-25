package com.example.shoppinglist.data.datasource.repositories

import com.example.shoppinglist.data.datasource.local.entities.toModel
import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.data.datasource.local.models.toEntity
import com.example.shoppinglist.data.datasource.shopping.ShoppingLocalDataSource
import com.example.shoppinglist.data.datasource.shopping.ShoppingRemoteDataSource
import com.example.shoppinglist.di.annotations.LocalDataSource
import com.example.shoppinglist.di.annotations.RemoteDataSource
import com.example.shoppinglist.utils.Resource
import com.example.shoppinglist.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    @LocalDataSource private val localDataSource: ShoppingLocalDataSource,
    @RemoteDataSource private val remoteDataSource: ShoppingRemoteDataSource
) : IShoppingRepository {
    override fun getShoppingList(): Flow<Resource<List<ShoppingModel>>> {
        return networkBoundResource(
            fetch = {
                remoteDataSource.getAllShopping()
            },
            query = {
                localDataSource.getAllShopping().map {
                    it.map { shoppingEntity -> shoppingEntity.toModel() }
                }
            },
            saveFetchResult = {
                it.forEach { shoppingEntity ->
                    localDataSource.insertShoppingItem(shoppingEntity.toEntity())
                }
            }
        )
    }

    override fun insertShopping(shopping: ShoppingModel): Flow<Resource<Boolean>> {
        return networkBoundResource(
            fetch = { flow { remoteDataSource.insertShoppingItem(shopping) } },
            query = { flow { localDataSource.insertShoppingItem(shopping.toEntity()) } },

            )
    }

    override fun updateShopping(shopping: ShoppingModel): Flow<Resource<Boolean>> {
        return networkBoundResource(
            fetch = { flow { remoteDataSource.updateShoppingItem(shopping) } },
            query = { flow { localDataSource.updateShoppingItem(shopping.toEntity()) } },
        )
    }

    override fun deleteShopping(shopping: ShoppingModel): Flow<Resource<Boolean>> {
        return networkBoundResource(
            fetch = { flow { remoteDataSource.deleteShoppingItem(shopping) } },
            query = { flow { localDataSource.updateShoppingItem(shopping.toEntity()) } },

            )
    }
}
package com.example.shoppinglist.data.datasource.shopping

import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.utils.await
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class ShoppingRemoteDataSource @Inject constructor(
    @Named("shopping") private val shoppingCollection: CollectionReference
) : ShoppingDataSource<ShoppingModel> {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllShopping() = callbackFlow {

        val a = shoppingCollection.addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            val list = value!!.toObjects(ShoppingModel::class.java)
            trySend(list)


        }

        awaitClose { a.remove() }
        invokeOnClose {
            print(1)
        }
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingModel): Boolean {
        val result = shoppingCollection.document(shoppingItem.id).set(shoppingItem).await()

        return true
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingModel): Boolean {
        shoppingCollection.document(shoppingItem.id).update(
            mapOf(

                "name" to shoppingItem.name,
                "amount" to shoppingItem.amount
            )
        ).await()
        return true
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingModel): Boolean {
        shoppingCollection.document(shoppingItem.id).delete().await()

        return true
    }

}
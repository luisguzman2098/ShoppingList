package com.example.shoppinglist.data.datasource.local.models

import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity
import java.util.*

data class ShoppingModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    var address: String = ""
)

fun ShoppingModel.toEntity() = ShoppingEntity(id, name, address)
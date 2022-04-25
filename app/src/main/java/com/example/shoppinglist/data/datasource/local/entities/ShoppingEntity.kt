package com.example.shoppinglist.data.datasource.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
@Entity(tableName = "shopping_items")
data class ShoppingEntity(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,

    )
fun ShoppingEntity.toModel() = ShoppingModel(
    id = id,
    name = name,
    amount = amount
)
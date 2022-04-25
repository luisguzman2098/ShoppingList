package com.example.shoppinglist.ui.shoppinglist

import com.example.shoppinglist.data.datasource.local.models.ShoppingModel

interface AddDialogListener {
    fun onAddButtonClicked(shoppingModel: ShoppingModel)
}
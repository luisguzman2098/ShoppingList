package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.datasource.local.entities.ShoppingEntity
import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.data.datasource.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun insert(shoppingModel: ShoppingModel) = repository.insertShopping(shoppingModel).asLiveData()

    fun updateItem(shoppingModel: ShoppingModel) = viewModelScope.launch {
        repository.updateShopping(shoppingModel).collect()
    }

    fun deleteItem(shoppingModel: ShoppingModel) = viewModelScope.launch {
        repository.deleteShopping(shoppingModel).collect()
    }

    fun getAllShoppingItems() = repository.getShoppingList().asLiveData()


}

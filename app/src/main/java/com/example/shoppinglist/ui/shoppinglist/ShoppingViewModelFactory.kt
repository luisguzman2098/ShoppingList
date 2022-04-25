package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.data.datasource.repositories.ShoppingRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@ActivityScoped
class ShoppingViewModelFactory @Inject constructor(private val repository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }

}
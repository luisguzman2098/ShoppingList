package com.example.shoppinglist.di.modules

import android.content.Context
import com.example.shoppinglist.data.datasource.local.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {


    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return ShoppingDatabase(context)
    }
}
package com.example.shoppinglist.di.modules

import com.example.shoppinglist.data.datasource.local.ShoppingDatabase
import com.example.shoppinglist.data.datasource.repositories.ShoppingRepository
import com.example.shoppinglist.data.datasource.shopping.ShoppingDataSource
import com.example.shoppinglist.data.datasource.shopping.ShoppingLocalDataSource
import com.example.shoppinglist.data.datasource.shopping.ShoppingRemoteDataSource
import com.example.shoppinglist.di.annotations.LocalDataSource
import com.example.shoppinglist.di.annotations.RemoteDataSource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ShoppingModule {

    @Provides
    fun provideShoppingRepository(
        @LocalDataSource localDataSource: ShoppingLocalDataSource,
        @RemoteDataSource remoteDataSource: ShoppingRemoteDataSource
    ): ShoppingRepository {
        return ShoppingRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
        )
    }

    @Provides
    @LocalDataSource
    fun provideLocalDataSource(shoppingDatabase: ShoppingDatabase): ShoppingLocalDataSource {
        return ShoppingLocalDataSource(shoppingDao = shoppingDatabase.getShoppingDao())
    }

    @Provides
    @RemoteDataSource
    fun provideRemoteDataSource(
        @Named("shopping") shoppingCollection: CollectionReference
    ): ShoppingRemoteDataSource {
        return ShoppingRemoteDataSource(
            shoppingCollection = shoppingCollection
        )
    }

}
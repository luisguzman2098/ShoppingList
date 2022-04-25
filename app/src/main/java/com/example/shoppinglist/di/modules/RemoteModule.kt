package com.example.shoppinglist.di.modules

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides @Named("user")
    fun provideUserCollection(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection("users")
    }

    @Provides @Named("shopping")
    fun provideShoppingCollection(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection("shopping")
    }
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

}
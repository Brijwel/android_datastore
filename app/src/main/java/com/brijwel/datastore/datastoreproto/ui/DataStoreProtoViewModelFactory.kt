package com.brijwel.datastore.datastoreproto.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brijwel.datastore.datastoreproto.repo.UserRepositoryImpl
import com.brijwel.datastore.datastoreproto.userPreferencesStore

class DataStoreProtoViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DataStoreProtoViewModel::class.java) -> DataStoreProtoViewModel(
                UserRepositoryImpl(
                    context.userPreferencesStore
                )
            ) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
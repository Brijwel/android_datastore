package com.brijwel.datastore.datastoreproto

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataStoreProtoViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DataStoreProtoViewModel::class.java) -> DataStoreProtoViewModel(
                UserRepository(
                    context.userPreferencesStore
                )
            ) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
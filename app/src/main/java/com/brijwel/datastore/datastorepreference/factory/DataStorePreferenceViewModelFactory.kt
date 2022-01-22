package com.brijwel.datastore.datastorepreference.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brijwel.datastore.datastorepreference.ui.DataStorePreferenceViewModel
import com.brijwel.datastore.datastorepreference.pref.PreferenceStorage


class DataStorePreferenceViewModelFactory(
    private val preferenceStorage: PreferenceStorage
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DataStorePreferenceViewModel::class.java) -> DataStorePreferenceViewModel(
                preferenceStorage
            ) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
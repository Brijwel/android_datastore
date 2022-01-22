package com.brijwel.datastore.datastorepreference.ui

import androidx.lifecycle.*
import com.brijwel.datastore.datastorepreference.model.Task
import com.brijwel.datastore.datastorepreference.pref.PreferenceStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataStorePreferenceViewModel(
    private val preferenceStorage: PreferenceStorage
) : ViewModel() {

    val isCompetedOnly = preferenceStorage.completedOnly
    val tasks = Transformations.map(isCompetedOnly.asLiveData()) {
        getTasked(it)
    }

    fun setIfCompletedOnly(completedOnly: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceStorage.setCompletedFilter(completedOnly)
        }
    }

    private fun getTasked(completedOnly: Boolean) = if (completedOnly) {
        Task.getTasks().filter { task -> task.completed }
    } else {
        Task.getTasks()
    }
}
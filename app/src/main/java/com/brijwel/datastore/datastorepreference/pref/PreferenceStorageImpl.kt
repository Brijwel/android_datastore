package com.brijwel.datastore.datastorepreference.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.brijwel.datastore.datastorepreference.pref.PreferenceStorageImpl.PreferencesKeys.PREF_ON_IS_COMPLETED_ONLY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceStorageImpl(
    private val dataStore: DataStore<Preferences>
):PreferenceStorage {

    companion object {
        const val PREFS_NAME = "preference_storage"
    }

    object PreferencesKeys {
        val PREF_ON_IS_COMPLETED_ONLY = booleanPreferencesKey("is_completed_only")
    }

    override val completedOnly: Flow<Boolean>
        get() = dataStore.data.map { it[PREF_ON_IS_COMPLETED_ONLY] ?: false }

    override suspend fun setCompletedFilter(complete: Boolean) {
        dataStore.edit {
            it[PREF_ON_IS_COMPLETED_ONLY] = complete
        }
    }
}
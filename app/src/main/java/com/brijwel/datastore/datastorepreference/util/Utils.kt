package com.brijwel.datastore.datastorepreference.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.brijwel.datastore.datastorepreference.pref.PreferenceStorageImpl

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PreferenceStorageImpl.PREFS_NAME)

fun Any.tag() = this::class.simpleName
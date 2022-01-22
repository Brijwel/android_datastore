package com.brijwel.datastore.datastoreproto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.brijwel.datastore.UserData

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
val Context.userPreferencesStore: DataStore<UserData> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserDataSerializer()
)
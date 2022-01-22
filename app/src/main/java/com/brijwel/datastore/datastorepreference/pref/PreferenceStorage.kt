package com.brijwel.datastore.datastorepreference.pref

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    val completedOnly: Flow<Boolean>
    suspend fun setCompletedFilter(complete: Boolean)
}
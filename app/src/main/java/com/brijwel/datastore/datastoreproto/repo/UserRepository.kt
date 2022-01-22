package com.brijwel.datastore.datastoreproto.repo

import com.brijwel.datastore.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userData: Flow<UserData>
    suspend fun updateUserPreference(
        firstName: String,
        lastName: String,
        dob: String,
        contact: String
    )
}
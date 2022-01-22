package com.brijwel.datastore.datastoreproto

import android.util.Log
import androidx.datastore.core.DataStore
import com.brijwel.datastore.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserRepository(private val userPreferencesStore: DataStore<UserData>) {

    val userData: Flow<UserData> = userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("Error", exception.message.toString())
                emit(UserData.getDefaultInstance())
            } else {
                throw exception
            }
        }


    suspend fun updateUserPreference(
        firstName: String,
        lastName: String,
        dob: String,
        contact: String
    ) {
        userPreferencesStore.updateData { preference ->
            preference.toBuilder().setFirstName(firstName).setLastName(lastName).setDob(dob)
                .setContact(contact).build()
        }
    }
}
package com.brijwel.datastore.datastoreproto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brijwel.datastore.datastoreproto.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataStoreProtoViewModel(
    private val userRepo: UserRepository
) : ViewModel() {

    val userData = userRepo.userData

    fun updateUserData(
        firstName: String,
        lastName: String,
        dob: String,
        contact: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        userRepo.updateUserPreference(firstName, lastName, dob, contact)
    }
}
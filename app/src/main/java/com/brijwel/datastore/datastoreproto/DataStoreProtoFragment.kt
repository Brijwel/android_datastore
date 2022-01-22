package com.brijwel.datastore.datastoreproto

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.brijwel.datastore.R
import com.brijwel.datastore.databinding.FragmentDataStoreProtoBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class DataStoreProtoFragment : Fragment(R.layout.fragment_data_store_proto) {

    private val viewModel: DataStoreProtoViewModel by viewModels {
        DataStoreProtoViewModelFactory(requireContext().applicationContext)
    }

    private val calender = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDataStoreProtoBinding.bind(view)
        binding.dob.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->

                    calender.set(Calendar.YEAR, year)
                    calender.set(Calendar.MONTH, monthOfYear)
                    calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd.MM.yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                    binding.dob.text = sdf.format(calender.time)

                },
                calender[Calendar.YEAR],
                calender[Calendar.MONTH],
                calender[Calendar.DAY_OF_MONTH]
            )
            datePicker.show()
        }

        binding.update.setOnClickListener {
            val firstName = binding.firstName.text.toString().trim()
            val lastName = binding.lastName.text.toString().trim()
            val dob = binding.dob.text.toString().trim()
            val contact = binding.contact.text.toString().trim()
            when {
                firstName.isEmpty() -> {
                    binding.firstName.error = "Enter first name"
                    return@setOnClickListener
                }
                dob.isEmpty() -> {
                    binding.dob.error = "Select your date of birth"
                    return@setOnClickListener
                }
                contact.isEmpty() || contact.length < 10 -> {
                    binding.contact.error = "Enter a valid contact"
                    return@setOnClickListener
                }
            }
            viewModel.updateUserData(firstName, lastName, dob, contact)

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userData.collectLatest { userData ->
                    binding.apply {
                        firstName.setText(userData.firstName)
                        lastName.setText(userData.lastName)
                        dob.text = userData.dob
                        contact.setText(userData.contact)
                    }
                }
            }
        }
    }

}
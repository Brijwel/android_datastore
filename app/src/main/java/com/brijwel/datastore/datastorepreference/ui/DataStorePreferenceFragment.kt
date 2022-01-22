package com.brijwel.datastore.datastorepreference.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.brijwel.datastore.R
import com.brijwel.datastore.databinding.FragmentDataStorePreferenceBinding
import com.brijwel.datastore.datastorepreference.factory.DataStorePreferenceViewModelFactory
import com.brijwel.datastore.datastorepreference.pref.PreferenceStorageImpl
import com.brijwel.datastore.datastorepreference.util.dataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataStorePreferenceFragment : Fragment(R.layout.fragment_data_store_preference) {

    private val viewModel: DataStorePreferenceViewModel by viewModels {
        DataStorePreferenceViewModelFactory(PreferenceStorageImpl(requireContext().applicationContext.dataStore))
    }

    private val taskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDataStorePreferenceBinding.bind(view)

        binding.recyclerView.adapter = taskAdapter
        binding.completed.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIfCompletedOnly(isChecked)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isCompetedOnly.collectLatest {
                    binding.completed.isChecked = it
                }
            }
        }
        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }
    }
}
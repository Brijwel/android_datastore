package com.brijwel.datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.brijwel.datastore.databinding.ActivityMainBinding
import com.brijwel.datastore.datastorepreference.ui.DataStorePreferenceFragment
import com.brijwel.datastore.datastoreproto.DataStoreProtoFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<DataStorePreferenceFragment>(R.id.fragmentContainer)
            }
        }


        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab == binding.tab.getTabAt(0)) {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<DataStorePreferenceFragment>(R.id.fragmentContainer)
                    }
                } else {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<DataStoreProtoFragment>(R.id.fragmentContainer)
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

        })
    }
}
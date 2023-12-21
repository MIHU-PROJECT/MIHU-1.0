package com.example.mihu.ui.worker.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mihu.R
import com.example.mihu.databinding.ActivityHomeWorkerBinding
import com.example.mihu.ui.profile.ProfileFragment
import com.example.mihu.ui.worker.history.HistoryWorkerFragment
import com.example.mihu.ui.worker.task.TaskFragment

class HomeWorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(TaskFragment())

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_task -> replaceFragment(TaskFragment())
                R.id.nav_history_worker -> replaceFragment(HistoryWorkerFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())

                else -> {


                }

            }

            true

        }


    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()


    }
}
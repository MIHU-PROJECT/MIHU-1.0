package com.example.mihu.ui.nav

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mihu.R
import com.example.mihu.databinding.ActivityBottomNavigatianBinding

class BottomNavigationWorker : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigatianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigatianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigatian)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_task, R.id.nav_history_worker, R.id.nav_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
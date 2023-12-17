package com.example.mihu.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mihu.R

class SecondOnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_onboarding, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Find ImageButton by ID
        val btnNext: TextView = view.findViewById(R.id.tv_next)

        // Setting click listener for the ImageButton
        btnNext.setOnClickListener {
            // Handle button click, for example, navigate to the next fragment
            viewPager?.currentItem = 2
        }
        return view
    }

}
package com.example.expensemanager

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        navigateMain()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun navigateMain() {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment) },
            3000L) }
    }





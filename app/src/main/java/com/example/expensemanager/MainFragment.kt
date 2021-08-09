package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var mainBinding : FragmentMainBinding? = null
    private val binding get() = mainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mainBinding = FragmentMainBinding.inflate(inflater,container,false)



        navigateAdd()

        return binding.root


    }

    private fun navigateAdd() {
        binding.btnAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }


}
package com.example.expensemanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.expensemanager.R
import com.example.expensemanager.view.base.BaseViewModel
import com.example.expensemanager.databinding.ActivityMainBinding
import com.example.expensemanager.data.local.AppDatabase
import com.example.expensemanager.data.repo.ExpenseRepository
import com.example.expensemanager.util.viewModelFactory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding




    private val repo by lazy { ExpenseRepository(AppDatabase(this)) }
    private val viewModel: BaseViewModel by viewModels {
        viewModelFactory { BaseViewModel(this.application, repo) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel
        initViews()


    }

    private fun initViews() {

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment?
            ?: return

        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav,navController)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }





}
package com.example.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.expensemanager.base.BaseViewModel
import com.example.expensemanager.databinding.ActivityMainBinding
import com.example.expensemanager.local.AppDatabase
import com.example.expensemanager.repo.ExpenseRepository
import com.example.expensemanager.util.viewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment

    private val repo by lazy { ExpenseRepository(AppDatabase(this)) }
    private val viewModel: BaseViewModel by viewModels {
        viewModelFactory { BaseViewModel(this.application, repo) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel
        initializeView()

    }

    private fun initializeView() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
            ?: return

    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}
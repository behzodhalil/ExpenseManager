package com.example.expensemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.base.BaseFragment
import com.example.expensemanager.base.BaseViewModel
import com.example.expensemanager.databinding.FragmentMainBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


import kotlin.math.abs


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding,BaseViewModel>() {


    private lateinit var expenseAdapter: ExpenseAdapter
    override val viewModel: BaseViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?) = FragmentMainBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        initializeView()
        observeExpense()

    }

    //function
    private fun setUpRecyclerView() = with(binding) {
        expenseAdapter = ExpenseAdapter()
        val recyclerView = binding.controlRecylerView
        recyclerView.adapter = expenseAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    // function
    private fun onTotalExpenseLoading(expense: List<Expense>) = with(binding) {
        val (totalIncome, totalExpense) = expense.partition { it.type == "Income" }
        val income = totalIncome.sumOf { it.amount }
        val expense = totalExpense.sumOf{ it.amount }
        incomeAmount.text = "+ ".plus(convertToGlobal(income))
        expenseAmount.text = "- ".plus(convertToGlobal(expense))
        balanceAmount.text = convertToGlobal(income - expense)
    }



    //function

    private fun observeExpense() = lifecycleScope.launchWhenCreated {
        viewModel.viewState.collect { viewState ->
            when(viewState){
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    showAllViews()
                    onExpenseLoaded(viewState.expense)
                    onTotalExpenseLoading(viewState.expense)
                }
                is ViewState.Empty ->{
                    hideAllViews()
                }
                is ViewState.Error -> {
                    toast("Error")
                }
            }
        }
    }

    private fun showAllViews() = with(binding) {
        mainRelative.show()
        emptyLayout.hide()
        controlRecylerView.show()
    }

    private fun hideAllViews() = with(binding) {
        mainRelative.hide()
        emptyLayout.show()
    }

    //function
    private fun onExpenseLoaded(list: List<Expense>)  = expenseAdapter.differ.submitList(list)

    //function
    private fun initializeView() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
        mainDashboardScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, sX, sY, oX, oY ->
                if (abs(sY - oY) > 10) {
                    when {
                        sY > oY -> btnAdd.hide()
                        oY > sY -> btnAdd.show()
                    }
                }
            }
        )
        expenseAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("expense",it)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
            bundle
        }
    }



}

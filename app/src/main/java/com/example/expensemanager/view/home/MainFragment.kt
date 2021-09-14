package com.example.expensemanager.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.view.base.BaseFragment
import com.example.expensemanager.view.base.BaseViewModel
import com.example.expensemanager.databinding.FragmentMainBinding
import com.example.expensemanager.data.model.Expense
import com.example.expensemanager.util.*
import com.example.expensemanager.view.adapter.ExpenseAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


import kotlin.math.abs


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, BaseViewModel>() {


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
        onClickExpense()
        swipeToDelete()
    }

    private fun onClickExpense() {
        expenseAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("expense",it)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment,bundle)

        }
    }

    private fun swipeToDelete() {
        // init item touch callback for swipe action
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // get item position & delete notes
                val position = viewHolder.adapterPosition
                val expense = expenseAdapter.differ.currentList[position]
                val expenseItem = Expense(
                    expense.title,
                    expense.amount,
                    expense.type,
                    expense.tag,
                    expense.date,
                    expense.note,
                    expense.createdate,
                    expense.id
                )
                viewModel.deleteExpense(expenseItem)
                initializeTotalLayout()
                Snackbar.make(
                    binding.root,
                    getString(R.string.successfully_delete),
                    Snackbar.LENGTH_LONG
                )
                    .apply {
                        setAction("Undo") {
                            viewModel.insertExpense(
                                expenseItem
                            )
                        }
                        show()
                    }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.controlRecyclerView)
        }
    }

    private fun initializeTotalLayout() = with(binding) {
        binding.expenseTotalView.totalBalanceAmount.text = "$ 0"
        binding.expenseTotalView.totalIncomeAmount.text = "$ 0"
        binding.expenseTotalView.totalExpenseAmount.text = "$ 0"
    }

    private fun setUpRecyclerView() = with(binding) {
        expenseAdapter = ExpenseAdapter()
        val recyclerView = binding.controlRecyclerView
        recyclerView.adapter = expenseAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun onTotalExpenseLoading(expense: List<Expense>) = with(binding) {
        val (totalIncome, totalExpense) = expense.partition { it.type == "Income" }
        val income = totalIncome.sumOf { it.amount }
        val expense = totalExpense.sumOf{ it.amount }
        expenseTotalView.totalIncomeAmount.text = "+ ".plus(convertToGlobal(income))
        expenseTotalView.totalExpenseAmount.text = "- ".plus(convertToGlobal(expense))
        expenseTotalView.totalBalanceAmount.text = convertToGlobal(income-expense)

    }

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
        controlRecyclerView.show()
    }

    private fun hideAllViews() = with(binding) {
        controlRecyclerView.hide()
        emptyLayout.show()
    }

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

    }



}

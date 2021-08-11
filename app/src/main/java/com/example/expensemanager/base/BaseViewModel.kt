package com.example.expensemanager.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.model.Expense
import com.example.expensemanager.repo.ExpenseRepository
import com.example.expensemanager.util.DetailState
import com.example.expensemanager.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BaseViewModel constructor(
    application: Application,
    private val repo: ExpenseRepository
) : AndroidViewModel(application)
{
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)

    private val viewState: StateFlow<ViewState> = _viewState
    private val detailState : StateFlow<DetailState> = _detailState


    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repo.insert(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repo.update(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repo.delete(expense)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        repo.deleteById(id)
    }

    fun getById(id: Int) = viewModelScope.launch {
        _detailState.value = DetailState.Loading
        repo.getById(id).collect { result: Expense? ->
            if (result != null) {
                _detailState.value = DetailState.Success(result)
            }
        }
    }


}


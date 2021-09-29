package com.example.expensemanager.view.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.viewModelScope
import com.example.expensemanager.data.model.Expense
import com.example.expensemanager.data.repo.ExpenseRepository
import com.example.expensemanager.util.DetailState
import com.example.expensemanager.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 ### `Common class for using viewModel classes`
 */

@HiltViewModel
class BaseViewModel @Inject  constructor(
    application: Application,
    private val repo: ExpenseRepository
) : AndroidViewModel(application)
{
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)


    val viewState: StateFlow<ViewState> = _viewState
    val detailState : StateFlow<DetailState> = _detailState


    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repo.insert(expense)
    }


    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repo.delete(expense)
    }


    fun getById(id: Int) = viewModelScope.launch {
        _detailState.value = DetailState.Loading
        repo.getById(id).collect { result: Expense? ->
            if (result != null) {
                _detailState.value = DetailState.Success(result)
            }
        }
    }
    init {
        //Does not block a main thread, dispatches on background thread
        viewModelScope.launch {
            repo.getAllItem().collect { result ->
                if(result.isNullOrEmpty()) {
                    _viewState.value = ViewState.Empty
                }
                else {
                    _viewState.value = ViewState.Success(result)
                }
            }
        }
    }


}




package com.example.expensemanager.util

import com.example.expensemanager.data.model.Expense

sealed class DetailState {
    object Loading : DetailState()
    object Empty : DetailState()
    data class Success(val expense: Expense) : DetailState()
    data class Error(val exception: Throwable) : DetailState()
}
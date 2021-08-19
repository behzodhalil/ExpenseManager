package com.example.expensemanager.repo

import android.os.Bundle
import com.example.expensemanager.local.AppDatabase
import com.example.expensemanager.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExpenseRepository constructor(private val database: AppDatabase) {

    suspend fun insert(expense: Expense) = database.getExpenseDao().insert(expense)

    suspend fun update(expense: Expense) = database.getExpenseDao().update(expense)

    suspend fun delete(expense: Expense) = database.getExpenseDao().delete(expense)

    fun getAllItem() = database.getExpenseDao().getAllExpenses()

    fun getById(id: Int) = database.getExpenseDao().getById(id)

    suspend fun deleteById(id: Int) = database.getExpenseDao().deleteById(id)


}
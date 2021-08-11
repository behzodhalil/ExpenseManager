package com.example.expensemanager.local

import androidx.room.*
import com.example.expensemanager.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER by createdate")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id = :id")
    fun getById(id:Int): Flow<Expense>

    @Query("DELETE FROM expense_table WHERE id = :id")
    suspend fun deleteById(id: Int)
}
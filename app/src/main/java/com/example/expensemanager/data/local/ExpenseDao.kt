package com.example.expensemanager.data.local

import androidx.room.*
import com.example.expensemanager.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER by create_date")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id = :id")
    fun getById(id:Int): Flow<Expense>

    @Query("DELETE FROM expense_table WHERE id = :id")
    suspend fun deleteById(id: Int)
}
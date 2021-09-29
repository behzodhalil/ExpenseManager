package com.example.expensemanager.data.local

import androidx.room.*
import com.example.expensemanager.data.model.Expense
import kotlinx.coroutines.flow.Flow

/*
* Provides access to read/write operations on the expense table.
* Used by the view model to format query, insert, delete results for use in the UI.
*/

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM ${Expense.TABLE_NAME} ORDER by create_date")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM ${Expense.TABLE_NAME} WHERE id = :id")
    fun getById(id:Int): Flow<Expense>

    @Query("DELETE FROM ${Expense.TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)

}

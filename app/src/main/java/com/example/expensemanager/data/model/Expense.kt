package com.example.expensemanager.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensemanager.data.model.Expense.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize


/**
### `Represents a single table in the database.`
*/

@Entity(tableName = TABLE_NAME)
@Parcelize
data class Expense(

    // @ColumnInfo used to customise your column in table
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "tag") var tag: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "create_date") var createDate: Long = System.currentTimeMillis(),
    // @PrimaryKey used to identify unique identifier for each row
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0

):Parcelable {
    companion object {
        const val TABLE_NAME = "expense_table"
    }
}
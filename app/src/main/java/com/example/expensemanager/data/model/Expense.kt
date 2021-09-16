package com.example.expensemanager.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat


@Entity(tableName = "expense_table")
@Parcelize
data class Expense(

    @ColumnInfo(name="title") var title: String,
    @ColumnInfo(name="amount") var amount:Double,
    @ColumnInfo(name="type") var type: String,
    @ColumnInfo(name="tag") var tag: String,
    @ColumnInfo(name="date") var date: String,
    @ColumnInfo(name="note") var note: String,
    @ColumnInfo(name="create_date") var createdate: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int = 0

): Parcelable
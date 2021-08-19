package com.example.expensemanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.databinding.ExpenseItemLayoutBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.util.convertToGlobal

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(val binding: ExpenseItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Expense>(){

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)



    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            itemText.text = item.title
            itemCategory.text = item.type


            when(item.type) {
                "Income" -> {
                    itemAmount.setTextColor(
                        ContextCompat.getColor(itemAmount.context,R.color.lime_green)
                    )
                    itemAmount.text = "+ ".plus(convertToGlobal(item.amount))
                }
                "Expense"-> {
                    itemAmount.setTextColor(
                        ContextCompat.getColor(itemAmount.context,R.color.flamingo)
                    )
                    itemAmount.text = "+ ".plus(convertToGlobal(item.amount))
                }
            }

            when(item.tag) {
                "Housing" -> {
                    itemIcon.setImageResource(R.drawable.ic_housing)
                }
                "Transportation"-> {
                    itemIcon.setImageResource(R.drawable.ic_transport)
                }
                "Food" -> {
                    itemIcon.setImageResource(R.drawable.ic_food)
                }
                "Utilities" -> {
                    itemIcon.setImageResource(R.drawable.ic_utilities)
                }
                "Insurance" -> {
                    itemIcon.setImageResource(R.drawable.ic_insurance)
                }
                "Healthcare" -> {
                    itemIcon.setImageResource(R.drawable.ic_medical)
                }
                else -> {
                    itemIcon.setImageResource(R.drawable.ic_income)
                }
            }

            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }

    }

    private var onItemClickListener : ((Expense) -> Unit)? = null

    fun setOnClickListener(listener: (Expense) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ExpenseItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExpenseViewHolder(binding)
    }
}
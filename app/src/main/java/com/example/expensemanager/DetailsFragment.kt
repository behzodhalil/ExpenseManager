package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.expensemanager.base.BaseFragment
import com.example.expensemanager.base.BaseViewModel
import com.example.expensemanager.databinding.FragmentDetailsBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.util.DetailState
import com.example.expensemanager.util.convertToGlobal
import kotlinx.coroutines.flow.collect
import kotlin.math.exp


class DetailsFragment : BaseFragment<FragmentDetailsBinding,BaseViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val args: DetailsFragmentArgs by navArgs()
    override val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expense = args.expense
        getById(expense.id)
        observeItems()
    }

    private fun getById(id: Int) {
        viewModel.getById(id)
    }

    private fun observeItems() = lifecycleScope.launchWhenCreated {

        viewModel.detailState.collect() {detailState ->

            when(detailState){
                DetailState.Loading -> {

                }
                is DetailState.Error -> {
                    toast("Error!")
                }
                is DetailState.Success -> {
                    onDetailsSaved(detailState.expense)
                }
            }

        }
    }

    private fun onDetailsSaved(expense: Expense) = with(binding.expenseDetailsLayout) {
        detailsTitle.text = expense.title
        detailsAmount.text = convertToGlobal(expense.amount)
        detailsType.text = expense.type
        detailsTag.text = expense.tag
        detailsDate.text = expense.date
        detailsDesc.text = expense.note

    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentDetailsBinding.inflate(inflater,container,false)



}
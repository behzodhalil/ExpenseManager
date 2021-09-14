package com.example.expensemanager.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expensemanager.view.base.BaseFragment
import com.example.expensemanager.view.base.BaseViewModel
import com.example.expensemanager.databinding.FragmentDetailsBinding
import com.example.expensemanager.data.model.Expense
import com.example.expensemanager.util.DetailState
import com.example.expensemanager.util.cleanTextContent
import com.example.expensemanager.util.convertToGlobal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, BaseViewModel>() {

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

        viewModel.detailState.collect { detailState ->

            when(detailState){
                DetailState.Loading -> {

                }
                is DetailState.Error -> {
                    toast("Error!")
                }
                is DetailState.Success -> {
                    onDetailsSaved(detailState.expense)
                }
                DetailState.Empty -> {
                    findNavController().navigateUp()
                }
            }

        }
    }

    private fun onDetailsSaved(expense: Expense) = with(binding.expenseDetailsLayout) {
        detailsTitle.text = expense.title
        detailsAmount.text = convertToGlobal(expense.amount).cleanTextContent
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
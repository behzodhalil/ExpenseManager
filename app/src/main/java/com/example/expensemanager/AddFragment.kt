package com.example.expensemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.view.base.BaseFragment
import com.example.expensemanager.view.base.BaseViewModel
import com.example.expensemanager.databinding.FragmentAddBinding
import com.example.expensemanager.model.data.Expense
import com.example.expensemanager.util.Constants
import com.example.expensemanager.util.modifyIntoDatePicker
import com.example.expensemanager.util.parseDouble
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {

        val expenseTypeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_autocomplete_layout,
            Constants.expenseType
        )

        val expenseTagAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_autocomplete_layout,
            Constants.expenseTags
        )

        with(binding) {
            addLayout.addType.setAdapter(expenseTypeAdapter)
            addLayout.addTag.setAdapter(expenseTagAdapter)

            addLayout.addDate.modifyIntoDatePicker(
                requireContext(),
                "dd/MM/E",
                 Date()
            )
            buttonSave.setOnClickListener {
                binding.addLayout.apply {
                val(title,amount,type,tag,date,desc) = getExpenseBinding()
                    when {
                        title.isEmpty() -> {
                            this.addTitle.error = "The title must not be empty"
                        }
                        amount.isNaN() -> {
                            this.addAmount.error = "This amount must not be empty"
                        }
                        type.isEmpty() -> {
                            this.addType.error = "This type must not be empty"
                        }
                        tag.isEmpty() -> {
                            this.addTag.error = "This tag must not be empty."
                        }
                        date.isEmpty() -> {
                            this.addDate.error = "This date must not be empty"
                        }
                        desc.isEmpty() -> {
                            this.addDesc.error = "This description must note be empty."
                        }
                        else -> {
                            viewModel.insertExpense(getExpenseBinding()).run {
                                toast(getString(R.string.successfully_save))
                                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
                            }
                        }
                    }
                }
            }


        }
    }

    private fun getExpenseBinding() : Expense = binding.addLayout.let {
        val title = it.addTitle.text.toString()
        val amount = parseDouble(it.addAmount.text.toString())
        val type = it.addType.text.toString()
        val tag = it.addTag.text.toString()
        val date = it.addDate.text.toString()
        val desc = it.addDesc.text.toString()

        return@getExpenseBinding Expense(title,amount,type,tag,date,desc)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?) = FragmentAddBinding.inflate(inflater, container, false)


}
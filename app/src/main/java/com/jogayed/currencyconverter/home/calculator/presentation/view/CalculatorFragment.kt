package com.jogayed.currencyconverter.home.calculator.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jogayed.core.presentation.view.BaseFragment
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.placeholder.PlaceholderContent

class CalculatorFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_calculator

    private lateinit var tvCalculatedAmount: TextView
    private lateinit var tvTargetCurrencyName: TextView
    private lateinit var edtInputAmount: EditText
    private lateinit var tvBaseCurrencyName: TextView

    private val inputAmountAfterTextChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // ignore
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // ignore
        }

        override fun afterTextChanged(s: Editable) {
            calculate(s.toString())
        }
    }

    override fun init() {
        super.init()
        tvCalculatedAmount = requireView().findViewById(R.id.tv_calculated_amount)
        tvTargetCurrencyName = requireView().findViewById(R.id.tv_target_currency_name)
        edtInputAmount = requireView().findViewById(R.id.edt_input_amount)
        tvBaseCurrencyName = requireView().findViewById(R.id.tv_base_currency_name)


        edtInputAmount.addTextChangedListener(inputAmountAfterTextChangedListener)
        edtInputAmount.setOnEditorActionListener { edtInputAmount, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                calculate(edtInputAmount.text.toString())
            }
            false
        }

    }

    private fun calculate(inputAmountString: String) {
        // TODO call viewModel.calculate(inputAmount)
        if (inputAmountString.isEmpty()) return
        val inputAmount = inputAmountString.toDouble()
        Toast.makeText(requireContext(), "$inputAmount", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "CalculatorFragment"

        @JvmStatic
        fun newInstance(targetCurrency: PlaceholderContent.PlaceholderItem) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}
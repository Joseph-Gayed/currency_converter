package com.jogayed.currencyconverter.home.calculator.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jogayed.core.presentation.view.BaseFragment
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.calculator.domain.action.CalculationAction
import com.jogayed.currencyconverter.home.calculator.domain.usecases.CalculationInputParams
import com.jogayed.currencyconverter.home.calculator.domain.viewstate.CalculationViewState
import com.jogayed.currencyconverter.home.calculator.presentation.viewmodel.CalculatorViewModel
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel
import com.jogayed.currencyconverter.home.rates_list.presentation.viewmodel.RatesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CalculatorFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_calculator

    private val ratesViewModel: RatesViewModel by activityViewModels()
    private val calculatorViewModel: CalculatorViewModel by viewModels()


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



        edtInputAmount.setOnEditorActionListener { edtInputAmount, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                calculate(edtInputAmount.text.toString())
            }
            false
        }

        showSelectedAndBaseRates()
    }

    private fun showSelectedAndBaseRates() {
        ratesViewModel.selectedCurrencyRate?.let { selected ->
            ratesViewModel.baseCurrencyRate?.let { base ->
                showRates(
                    selected,
                    base
                )
            }
        }
    }

    private fun showRates(
        selectedRate: CurrencyRateUiModel,
        baseRate: CurrencyRateUiModel
    ) {
        tvCalculatedAmount.text = selectedRate.rate.toString()
        tvTargetCurrencyName.text = selectedRate.name
        tvBaseCurrencyName.text = baseRate.name
        edtInputAmount.setText(baseRate.rate.toString())
        edtInputAmount.addTextChangedListener(inputAmountAfterTextChangedListener)
    }

    private fun calculate(inputAmountString: String) {
        if (inputAmountString.isEmpty()) return
        val inputAmount = inputAmountString.toDouble()
        ratesViewModel.selectedCurrencyRate?.rate?.let { targetRate ->
            calculatorViewModel.executeAction(
                CalculationAction.CalculateRate(
                    CalculationInputParams(
                        baseRate = ratesViewModel.baseCurrencyRate?.rate ?: 1.toDouble(),
                        targetRate = targetRate,
                        inputAmount = inputAmount
                    )
                )
            )

        }

    }

    override fun subscribe() {
        super.subscribe()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            calculatorViewModel.viewStates.collect { viewState ->
                if (viewState is CalculationViewState.SUCCESS) {
                    tvCalculatedAmount.text = viewState.value.toString()
                }
            }
        }
    }

    companion object {
        const val TAG = "CalculatorFragment"

        @JvmStatic
        fun newInstance() =
            CalculatorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}
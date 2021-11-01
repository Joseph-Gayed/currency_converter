package com.jogayed.currencyconverter.home

import com.jogayed.core.presentation.view.BaseActivity
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.calculator.presentation.view.CalculatorFragment
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel
import com.jogayed.currencyconverter.home.rates_list.presentation.view.CurrencyRatesListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeActivity : BaseActivity(), CurrencyRatesListFragment.CurrencyItemClickListener {
    override fun getLayoutResId() = R.layout.activity_home

    override fun onCurrencyItemClicked() {
        openCalculatorFragment()
    }

    private fun openCalculatorFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.home_fragments_container, CalculatorFragment.newInstance())
            .addToBackStack(CalculatorFragment.TAG)
            .commit()
    }


}
package com.jogayed.currencyconverter.home

import com.jogayed.core.presentation.view.BaseActivity
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.calculator.presentation.view.CalculatorFragment
import com.jogayed.currencyconverter.home.placeholder.PlaceholderContent
import com.jogayed.currencyconverter.home.rates_list.presentation.view.CurrencyRatesListFragment

class HomeActivity : BaseActivity(), CurrencyRatesListFragment.CurrencyItemClickListener {
    override fun getLayoutResId() = R.layout.activity_home

    override fun onCurrencyItemClicked(clickedItem: PlaceholderContent.PlaceholderItem) {
        openCalculatorFragment(clickedItem)
    }

    private fun openCalculatorFragment(clickedItem: PlaceholderContent.PlaceholderItem) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragments_container, CalculatorFragment.newInstance(clickedItem))
            .addToBackStack(CalculatorFragment.TAG)
            .commit()
    }


}
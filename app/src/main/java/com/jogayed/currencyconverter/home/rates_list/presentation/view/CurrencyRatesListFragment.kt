package com.jogayed.currencyconverter.home.rates_list.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jogayed.core.presentation.view.BaseFragment
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class CurrencyRatesListFragment : BaseFragment() {

    var itemClickListener: CurrencyItemClickListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemClickListener = context as? CurrencyItemClickListener
    }


    override fun getLayoutResId() = R.layout.fragment_currency_rates_list


    override fun init() {
        super.init()
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrencyRatesListAdapter(PlaceholderContent.ITEMS) { item ->
                itemClickListener?.onCurrencyItemClicked(item)
            }
        }
    }



    interface CurrencyItemClickListener {
        fun onCurrencyItemClicked(clickedItem: PlaceholderContent.PlaceholderItem)
    }
}
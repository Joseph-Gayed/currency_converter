package com.jogayed.currencyconverter.home.rates_list.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jogayed.core.presentation.utils.BaseRecyclerViewAdapter
import com.jogayed.core.presentation.utils.listen
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel

/**
 * [RecyclerView.Adapter] that can display a [CurrencyRateUiModel].
 */
class CurrencyRatesListAdapter(
    private val itemClickListener: (item: CurrencyRateUiModel) -> Unit
) : BaseRecyclerViewAdapter<CurrencyRateUiModel,CurrencyRatesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency_rate, parent, false)

        return ViewHolder(view).listen { position, _ ->
            itemClickListener.invoke(items[position])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvCurrencyName.text = item.name
        holder.tvCurrencyRate.text = item.rate.toString()
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvCurrencyName: TextView = view.findViewById(R.id.spin_base_currency_name)
        val tvCurrencyRate: TextView = view.findViewById(R.id.tv_currency_rate)
    }
}
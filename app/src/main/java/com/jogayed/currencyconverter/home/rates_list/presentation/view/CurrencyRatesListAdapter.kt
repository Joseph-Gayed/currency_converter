package com.jogayed.currencyconverter.home.rates_list.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jogayed.core.presentation.utils.listen
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CurrencyRatesListAdapter(
    private val values: List<PlaceholderItem>,
    private val itemClickListener: (item: PlaceholderItem) -> Unit
) : RecyclerView.Adapter<CurrencyRatesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency_rate, parent, false)

        return ViewHolder(view).listen { position, _ ->
            itemClickListener.invoke(values[position])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvCurrencyName.text = item.content
        holder.tvCurrencyRate.text = item.details
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvCurrencyName: TextView = view.findViewById(R.id.tv_base_currency_name)
        val tvCurrencyRate: TextView = view.findViewById(R.id.tv_currency_rate)
    }
}
package com.jogayed.currencyconverter.home.rates_list.presentation.view

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jogayed.core.presentation.view.BaseFragment
import com.jogayed.core.presentation.viewmodel.DataState
import com.jogayed.core.presentation.viewmodel.isEmptyList
import com.jogayed.currencyconverter.R
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel
import com.jogayed.currencyconverter.home.rates_list.presentation.viewmodel.RatesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class CurrencyRatesListFragment : BaseFragment() {

    private var itemClickListener: CurrencyItemClickListener? = null

    private val viewModel: RatesViewModel by activityViewModels()

    private lateinit var baseCurrencyView: View
    private lateinit var tvBaseCurrencyRate: TextView
    private lateinit var spinBaseCurrencyName: Spinner


    private lateinit var rvRates: RecyclerView
    private lateinit var swipeRates: SwipeRefreshLayout
    private lateinit var loadingView: View
    private lateinit var errorView: View
    private lateinit var emptyView: View
    private lateinit var tvErrorMessage: TextView
    private val ratesAdapter: CurrencyRatesListAdapter by lazy {
        CurrencyRatesListAdapter { item ->
            viewModel.selectedCurrencyRate = item
            itemClickListener?.onCurrencyItemClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemClickListener = context as? CurrencyItemClickListener
    }


    override fun getLayoutResId() = R.layout.fragment_currency_rates_list


    override fun init() {
        super.init()
        initUi()
        viewModel.getRates(false)
    }

    private fun initUi() {
        baseCurrencyView = requireView().findViewById(R.id.view_base_currency)
        tvBaseCurrencyRate = requireView().findViewById(R.id.tv_base_currency_rate)
        spinBaseCurrencyName = requireView().findViewById(R.id.spin_base_currency_name)

        loadingView = requireView().findViewById(R.id.view_loading)
        errorView = requireView().findViewById(R.id.view_error)
        emptyView = requireView().findViewById(R.id.view_no_data)
        tvErrorMessage = requireView().findViewById(R.id.tv_error)

        swipeRates = requireView().findViewById(R.id.swipe_rates)
        swipeRates.setOnRefreshListener {
            viewModel.refresh()
        }
        rvRates = requireView().findViewById(R.id.rv_rates)
        with(rvRates) {
            layoutManager = LinearLayoutManager(context)
            adapter = ratesAdapter
        }
    }



    override fun subscribe() {
        super.subscribe()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.states.collect { state ->
                handleDataState(state)
            }
        }
    }

    private fun handleDataState(state: DataState<List<CurrencyRateUiModel>>) {
        baseCurrencyView.isVisible = state is DataState.Success || state.data() != null

        swipeRates.isRefreshing = state is DataState.Loading && state.data() != null
        loadingView.isVisible = state is DataState.Loading && state.data() == null
        errorView.isVisible = state is DataState.Error
        emptyView.isVisible = state.isEmptyList()
        if (state is DataState.Success) handleSuccessState(state.data)
        else if (state is DataState.Error) {
            tvErrorMessage.text = state.throwable.message
            state.throwable.printStackTrace()
        }
    }

    private fun handleSuccessState(data: List<CurrencyRateUiModel>) {
        if (data.isNotEmpty()) {
            ratesAdapter.setData(data)
            tvBaseCurrencyRate.text = viewModel.baseCurrencyRate?.rate.toString()

            val currenciesNames = data.map {
                it.name
            }

            initCurrencyBaseRatesSpinner(currenciesNames)

        }
    }
    private fun initCurrencyBaseRatesSpinner(currenciesNames: List<String>) {
        with(spinBaseCurrencyName) {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, position: Int, id: Long
                ) {
                    val newBaseRate = parent.getItemAtPosition(position).toString()
                    if (viewModel.nameOfCurrentBaseCurrency != newBaseRate) {
                        viewModel.changeBaseRate(newBaseRate)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    return;
                }
            }
            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                currenciesNames
            )
            adapter = spinnerAdapter
            setSelection(spinnerAdapter.getPosition(viewModel.nameOfCurrentBaseCurrency))
        }
    }

    interface CurrencyItemClickListener {
        fun onCurrencyItemClicked()
    }
}
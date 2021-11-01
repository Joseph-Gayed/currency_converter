package com.jogayed.currencyconverter.home.calculator.presentation.viewmodel

import com.jogayed.core.presentation.utils.reFormatDecimalPlaces
import com.jogayed.core.presentation.viewmodel.MVIBaseViewModel
import com.jogayed.currencyconverter.home.calculator.domain.action.CalculationAction
import com.jogayed.currencyconverter.home.calculator.domain.result.CalculationResult
import com.jogayed.currencyconverter.home.calculator.domain.usecases.CalculateUseCase
import com.jogayed.currencyconverter.home.calculator.domain.viewstate.CalculationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    val useCase: CalculateUseCase
) : MVIBaseViewModel<CalculationViewState, CalculationAction, CalculationResult>() {
    override val defaultInternalViewState: CalculationViewState
        get() = CalculationViewState.Idle


    override fun handleAction(action: CalculationAction): Flow<CalculationResult> {
        val flow: Flow<CalculationResult> = flow {
            when (action) {
                is CalculationAction.CalculateRate -> {
                    emit(CalculationResult.IDLE)
                    val useCaseResult = useCase(action.calculationInputInputs)

                    if (useCaseResult is CalculationResult.Success)
                        emit(formatOutputOfUseCaseResult(useCaseResult))
                    else
                        emit(useCaseResult)
                }
            }
        }
        return flow
    }

    private fun formatOutputOfUseCaseResult(result: CalculationResult.Success): CalculationResult {
        return CalculationResult.Success(result.value.reFormatDecimalPlaces())
    }


}
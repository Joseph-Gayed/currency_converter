package com.jogayed.core.presentation.viewmodel


sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Error(val throwable: Throwable) : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
}

fun <T> DataState<List<T>>.isEmptyList(): Boolean {
    return this is DataState.Success && this.data.isEmpty()
}




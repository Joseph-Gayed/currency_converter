package com.jogayed.core.presentation.view

import androidx.appcompat.widget.SearchView
import com.jogayed.core.presentation.utils.hideSoftKeyboard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*


/**
 * Add text watcher to the search view and emits all text changes in a flow
 */
private fun SearchView.getQueryTextChangeStateFlow(): Flow<String> {
    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            context.hideSoftKeyboard()
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query
}


/**
 * Apply some operators to the method returned from the [getQueryTextChangeStateFlow] method
 * then returns a flow of filtered query inputs
 */
@ExperimentalCoroutinesApi
@FlowPreview
fun SearchView.getQueryFlow(): Flow<String> {
    return getQueryTextChangeStateFlow()
        .debounce(500)
        .filter { query ->
            return@filter query.isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                emit(query)
            }
        }

}


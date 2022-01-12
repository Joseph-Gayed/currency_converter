package com.jogayed.core.presentation.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment


fun Context.hideSoftKeyboard() {
    val windowToken = when (this) {
        is Fragment -> {
            requireView().windowToken
        }
        is Activity -> {
            currentFocus?.windowToken
        }
        else -> {
            val baseContext = (this as ContextWrapper).baseContext
            (baseContext as? Activity)?.currentFocus?.windowToken
        }
    }

    val inputManager: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}


@SuppressLint("ClickableViewAccessibility")
fun Context.hideKeyboardWhenClickingOut(view: View) {
    // Set up touch listener for non-text box views endDate hide keyboard.
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideSoftKeyboard()
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
        for (i in 0 until (view as ViewGroup).childCount) {
            val innerView = (view as ViewGroup).getChildAt(i)
            hideKeyboardWhenClickingOut(innerView)
        }
    }
}
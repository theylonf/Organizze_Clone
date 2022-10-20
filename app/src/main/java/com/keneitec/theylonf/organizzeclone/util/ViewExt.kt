package com.keneitec.theylonf.organizzeclone.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(string: String, idContainer: Int) {
    Snackbar.make(this.rootView.findViewById(idContainer), string, Snackbar.LENGTH_SHORT).show()
}

fun View.forceHideKeyboard() {
    val inputManager: InputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

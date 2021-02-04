package com.example.testpromptnow

import android.text.Editable
import android.text.TextWatcher

class TextWatcherAdapter(private var field: (String) -> Unit) : TextWatcher {

    private var isInEditMode = false
    private var tmpSearch = ""

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        setText(s.toString())
    }

    fun setText(s: String) {
        if (tmpSearch != s) {
            isInEditMode = true
            field.invoke(s)
            tmpSearch = s
            isInEditMode = false
        }
    }
}

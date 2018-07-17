package com.skolimowskim.dietassistant.util

import com.google.android.material.textfield.TextInputEditText

object TextUtils {

    fun getIntValueOfText(textInput: TextInputEditText): Int {
        return if (textInput.text.toString() != "") textInput.text.toString().toInt() else 0
    }

}
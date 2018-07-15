package com.skolimowskim.dietassistant.util

import android.widget.EditText

object TextUtils {

    fun getIntValueOfText(et: EditText): Int {
        return if (et.text.toString() != "") et.text.toString().toInt() else 0
    }

}
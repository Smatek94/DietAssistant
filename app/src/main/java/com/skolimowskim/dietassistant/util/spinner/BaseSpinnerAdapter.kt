package com.skolimowskim.dietassistant.util.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

abstract class BaseSpinnerAdapter<T>(context: Context, spinnerLayout: Int, spinnerItemLayout: Int) :
        ArrayAdapter<T>(context, spinnerLayout) {

    init {
        this.addAll(this.getList())
        this.setDropDownViewResource(spinnerItemLayout)
    }

    // ********************************************************************************************************************************

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        return getCurrentTextView(textView, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        return getCurrentTextView(textView, position)
    }

    // ********************************************************************************************************************************

    abstract fun getCurrentTextView(textView: TextView, position: Int): TextView

    abstract fun getList(): Collection<T>

}
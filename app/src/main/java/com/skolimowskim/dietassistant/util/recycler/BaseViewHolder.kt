package com.skolimowskim.dietassistant.util.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun populate(data: T)
}

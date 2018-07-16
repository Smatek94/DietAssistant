package com.skolimowskim.dietassistant.view.meals.adapter

import android.view.View
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class MealViewHolder(itemView: View, listener: OnItemSelectedListener<Meal>) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var meal: Meal

    init {
        itemView.setOnClickListener { listener.onItemSelected(meal) }
    }

    override fun populate(data: BaseViewItem) {
        this.meal = data as Meal
    }

}

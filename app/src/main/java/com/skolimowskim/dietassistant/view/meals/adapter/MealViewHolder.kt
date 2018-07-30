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
    private val mealNameText: TextView
    private val carbo: TextView
    private val protein: TextView
    private val fat: TextView
    private val kcal: TextView

    init {
        itemView.setOnClickListener { listener.onItemSelected(meal) }
        mealNameText = itemView.findViewById(R.id.meal_name_text)
        carbo = itemView.findViewById(R.id.carbo_text)
        protein = itemView.findViewById(R.id.protein_text)
        fat = itemView.findViewById(R.id.fat_text)
        kcal = itemView.findViewById(R.id.kcal_text)
    }

    override fun populate(data: BaseViewItem) {
        this.meal = data as Meal
        mealNameText.text = meal.name
        carbo.text = meal.carbo.toString()
        protein.text = meal.protein.toString()
        fat.text = meal.fat.toString()
        kcal.text = meal.kcal.toString()
    }

}

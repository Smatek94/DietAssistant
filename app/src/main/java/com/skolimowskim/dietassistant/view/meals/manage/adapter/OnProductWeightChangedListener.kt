package com.skolimowskim.dietassistant.view.meals.manage.adapter

import com.skolimowskim.dietassistant.model.meal.ProductInMeal

interface OnProductWeightChangedListener {
    fun productWeightChanged(weight: Int, position: Int)
}
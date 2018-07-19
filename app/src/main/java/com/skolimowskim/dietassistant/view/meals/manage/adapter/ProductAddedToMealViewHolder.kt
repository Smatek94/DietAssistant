package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductAddedToMealViewHolder(itemView: View, listener: OnItemSelectedListener<ProductInMeal>) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var productInMeal: ProductInMeal
    private val productNameText: TextView
    private val carbo: TextView
    private val protein: TextView
    private val fat: TextView
    private val kcal: TextView
    private val gram: TextInputEditText

    init {
        itemView.setOnClickListener { listener.onItemSelected(productInMeal) }
        productNameText = itemView.findViewById(R.id.product_name_text)
        carbo = itemView.findViewById(R.id.carbo_text)
        protein = itemView.findViewById(R.id.protein_text)
        fat = itemView.findViewById(R.id.fat_text)
        kcal = itemView.findViewById(R.id.kcal_text)
        gram = itemView.findViewById(R.id.gram_input)
    }

    override fun populate(data: BaseViewItem) {
        this.productInMeal = data as ProductInMeal
        val product = productInMeal.product
        productNameText.text = product.name
        carbo.text = product.carbo.toString()
        protein.text = product.protein.toString()
        fat.text = product.fat.toString()
        kcal.text = product.kcal.toString()
        gram.setText(productInMeal.weight.toString())
    }

}

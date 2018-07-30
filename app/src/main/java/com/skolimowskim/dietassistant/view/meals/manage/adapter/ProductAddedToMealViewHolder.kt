package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductAddedToMealViewHolder(itemView: View,
                                   listener: OnItemSelectedListener<ProductInMeal>,
                                   onProductWeightChangedListener: OnProductWeightChangedListener) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var productInMeal: ProductInMeal
    private val productNameText: TextView
    private val carbo: TextView
    private val protein: TextView
    private val fat: TextView
    private val kcal: TextView
    private val gram: TextInputEditText

    private var test: Boolean = true // todo rename

    private var positionInAdapter: Int = 0

    init {
        itemView.setOnClickListener { listener.onItemSelected(productInMeal) }
        productNameText = itemView.findViewById(R.id.product_name_text)
        carbo = itemView.findViewById(R.id.carbo_text)
        protein = itemView.findViewById(R.id.protein_text)
        fat = itemView.findViewById(R.id.fat_text)
        kcal = itemView.findViewById(R.id.kcal_text)
        gram = itemView.findViewById(R.id.gram_input)
        gram.addTextChangedListener(object : TextWatcher { // todo change to simpleTextWatcher
            override fun afterTextChanged(text: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (test && text != null && text.isNotEmpty()) {
                    onProductWeightChangedListener.productWeightChanged(text.toString().toInt(), positionInAdapter)
                    productInMeal.weight = text.toString().toInt()
                    updateMacro()
                }
            }
        })
    }

    fun populate(data: BaseViewItem, position: Int) {
        this.positionInAdapter = position
        populate(data)
    }

    override fun populate(data: BaseViewItem) {
        this.productInMeal = data as ProductInMeal
        updateMacro()
        test = false
        gram.setText(productInMeal.weight.toString())
        test = true
    }

    private fun updateMacro() {
        val product = productInMeal.product
        productNameText.text = product.name
        carbo.text = (productInMeal.weight * product.carbo / 100).toString()
        protein.text = (productInMeal.weight * product.protein / 100).toString()
        fat.text = (productInMeal.weight * product.fat / 100).toString()
        kcal.text = (productInMeal.weight * product.kcal / 100).toString()
    }

}

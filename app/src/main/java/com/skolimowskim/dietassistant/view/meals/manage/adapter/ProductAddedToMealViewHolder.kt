package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.model.product.Product
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

    init {
        itemView.setOnClickListener { listener.onItemSelected(productInMeal) }
        productNameText = itemView.findViewById(R.id.product_name_text)
        carbo = itemView.findViewById(R.id.carbo_text)
        protein = itemView.findViewById(R.id.protein_text)
        fat = itemView.findViewById(R.id.fat_text)
        kcal = itemView.findViewById(R.id.kcal_text)
        gram = itemView.findViewById(R.id.gram_input)
        gram.addTextChangedListener(object : TextWatcher { // todo change to simpleTextWatcher
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(text != null && text.isNotEmpty()){
                    onProductWeightChangedListener.productWeightChanged(text.toString().toInt())
                }
            }
        })
    }

    override fun populate(data: BaseViewItem) {
        this.productInMeal = data as ProductInMeal
        val product = productInMeal.product
        productNameText.text = product.name
        val weightPercent = productInMeal.weight / 100
        carbo.text = (weightPercent * product.carbo).toString()
        protein.text = (weightPercent * product.protein).toString()
        fat.text = (weightPercent * product.fat).toString()
        kcal.text = (weightPercent * product.kcal).toString()
        gram.setText(productInMeal.weight.toString())
    }

}

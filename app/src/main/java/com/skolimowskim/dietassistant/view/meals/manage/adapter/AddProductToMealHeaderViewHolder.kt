package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.View
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductAddedToMealViewHolder(itemView: View, listener: OnItemSelectedListener<Product>) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var product: Product
    private val productNameText: TextView
    private val carbo: TextView
    private val protein: TextView
    private val fat: TextView
    private val kcal: TextView

    init {
        itemView.setOnClickListener { listener.onItemSelected(product) }
        productNameText = itemView.findViewById(R.id.product_name_text)
        carbo = itemView.findViewById(R.id.carbo_text)
        protein = itemView.findViewById(R.id.protein_text)
        fat = itemView.findViewById(R.id.fat_text)
        kcal = itemView.findViewById(R.id.kcal_text)
    }

    override fun populate(data: BaseViewItem) {
        this.product = data as Product
        productNameText.text = product.name
        carbo.text = product.carbo.toString()
        protein.text = product.protein.toString()
        fat.text = product.fat.toString()
        kcal.text = product.kcal.toString()
    }

}

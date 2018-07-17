package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.View
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class AddProductToMealHeaderViewHolder(itemView: View) : BaseViewHolder<BaseViewItem>(itemView) {

    init {
//        itemView.setOnClickListener { listener.onItemSelected(product) }
    }

    override fun populate(data: BaseViewItem) {
        /*this.product = data as Product
        productNameText.text = product.name
        carbo.text = product.carbo.toString()
        protein.text = product.protein.toString()
        fat.text = product.fat.toString()
        kcal.text = product.kcal.toString()*/
    }

}

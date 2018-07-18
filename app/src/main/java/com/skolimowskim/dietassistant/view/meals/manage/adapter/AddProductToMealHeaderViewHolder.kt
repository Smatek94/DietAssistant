package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.View
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.view.meals.manage.OnAddProductClicked

class AddProductToMealHeaderViewHolder(itemView: View, onAddProductClickedListener: OnAddProductClicked) : BaseViewHolder<BaseViewItem>(itemView) {

    init {
        itemView.setOnClickListener { onAddProductClickedListener.onAddProductClicked() }
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

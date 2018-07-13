package com.skolimowskim.dietassistant.view.products.adapter

import android.view.View
import com.skolimowskim.dietassistant.model.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductViewHolder(itemView: View, listener: OnItemSelectedListener<Product>) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var product: Product

    init {
        itemView.setOnClickListener{ listener.onItemSelected(product) }
    }

    override fun populate(data: BaseViewItem) {
        this.product = data as Product
    }

}

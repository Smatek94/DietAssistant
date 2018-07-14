package com.skolimowskim.dietassistant.view.products.adapter

import android.view.View
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductViewHolder(itemView: View, listener: OnItemSelectedListener<Product>) : BaseViewHolder<BaseViewItem>(itemView) {

    private lateinit var product: Product
    private val uuid: TextView

    init {
        itemView.setOnClickListener { listener.onItemSelected(product) }
        uuid = itemView.findViewById(R.id.uuid)
    }

    override fun populate(data: BaseViewItem) {
        this.product = data as Product
        uuid.text = product.uuid
    }

}

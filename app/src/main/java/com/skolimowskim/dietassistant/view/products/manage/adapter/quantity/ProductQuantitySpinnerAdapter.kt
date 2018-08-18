package com.skolimowskim.dietassistant.view.products.manage.adapter.quantity

import android.content.Context
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.product.ProductCategory
import com.skolimowskim.dietassistant.model.product.ProductQuantity
import com.skolimowskim.dietassistant.util.spinner.BaseSpinnerAdapter

class ProductQuantitySpinnerAdapter(context: Context) :
        BaseSpinnerAdapter<ProductQuantity>(context, R.layout.spinner_product_quantity, R.layout.spinner_product_quantity) {
    //fixme spinner width is acting like match parent

    override fun getList(): Collection<ProductQuantity> {
        val productQuantityList = ArrayList(ProductQuantity.values().toList())
        productQuantityList.remove(ProductQuantity.UNKNOWN)
        return productQuantityList
    }

    override fun getCurrentTextView(textView: TextView, position: Int): TextView {
        val productQuantity = getItem(position)
        textView.setText(productQuantity.title)
        return textView
    }

    fun getItemPosition(productQuantity: ProductQuantity): Int {
        for (i in 0..count) {
            if (getItem(i) == productQuantity) {
                return i
            }
        }
        return 0
    }
}
package com.skolimowskim.dietassistant.view.products.manage.adapter.category

import android.content.Context
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.product.ProductCategory
import com.skolimowskim.dietassistant.util.spinner.BaseSpinnerAdapter

class ProductCategorySpinnerAdapter(context: Context) :
        BaseSpinnerAdapter<ProductCategory>(context, R.layout.spinner_product_category, R.layout.spinner_product_category_item) {

    override fun getList(): Collection<ProductCategory> {
        val productCategoryList = ArrayList(ProductCategory.values().toList())
        productCategoryList.remove(ProductCategory.UNKNOWN)
        return productCategoryList
    }

    override fun getCurrentTextView(textView: TextView, position: Int): TextView {
        val productCategory = getItem(position)
        textView.setText(productCategory.title)
        return textView
    }

    fun getItemPosition(productCategory: ProductCategory): Int {
        for (i in 0..count) {
            if (getItem(i) == productCategory) {
                return i
            }
        }
        return 0
    }
}
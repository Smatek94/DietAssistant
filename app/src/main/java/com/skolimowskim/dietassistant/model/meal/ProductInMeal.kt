package com.skolimowskim.dietassistant.model.meal

import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductInMeal(val product: Product, var weight: Int) : BaseViewItem() {

    override fun equals(other: Any?): Boolean {
        if (other != null) {
            val productInMeal = other as ProductInMeal
            return productInMeal.product.uuid == product.uuid
        }
        return false
    }

}
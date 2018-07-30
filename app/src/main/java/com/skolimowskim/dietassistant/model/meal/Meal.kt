package com.skolimowskim.dietassistant.model.meal

import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import java.io.Serializable

class Meal(val carbo: Int = 0,
           val protein: Int = 0,
           val fat: Int = 0,
           val productList: ArrayList<ProductInMeal> = ArrayList(),
           var uuid: String = "") :
        BaseViewItem(), Serializable {

    fun updateProductWeight(weight: Int, productInMeal: ProductInMeal) {
        val productToUpdate = productList.find { it.product.uuid == productInMeal.product.uuid }
        productToUpdate?.weight = weight
    }
}

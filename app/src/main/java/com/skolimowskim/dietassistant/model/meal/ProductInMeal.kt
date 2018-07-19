package com.skolimowskim.dietassistant.model.meal

import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem

class ProductInMeal(val product: Product, val weight: Int) : BaseViewItem(){
}
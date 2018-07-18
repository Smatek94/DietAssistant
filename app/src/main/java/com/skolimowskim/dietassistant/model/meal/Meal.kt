package com.skolimowskim.dietassistant.model.meal

import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import java.io.Serializable

class Meal(val carbo: Int = 0,
           val protein: Int = 0,
           val fat: Int = 0,
           val productList : ArrayList<Product> = ArrayList()) :
        BaseViewItem(), Serializable
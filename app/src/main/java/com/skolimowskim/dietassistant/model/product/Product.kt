package com.skolimowskim.dietassistant.model.product

import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import java.io.Serializable

class Product(val name: String,
              val carbo: Int,
              val protein: Int,
              val fat: Int,
              val kcal: Int,
              val productCategory: ProductCategory,
              val productQuantity: ProductQuantity,
              val productQuantityWeight: Int,
              var uuid: String = "") : BaseViewItem(), Serializable {

}

package com.skolimowskim.dietassistant.model.product

import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.view.products.manage.ProductCategory
import java.io.Serializable
import java.util.*

class Product(val name: String,
              val carbo: Int,
              val protein: Int,
              val fat: Int,
              val kcal: Int,
              val productCategory: ProductCategory,
              var uuid: String = "") : BaseViewItem(), Serializable

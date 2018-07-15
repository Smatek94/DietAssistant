package com.skolimowskim.dietassistant.model

import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import java.io.Serializable
import java.util.*

class Product(val name: String,
              val carbo: Int,
              val protein: Int,
              val fat: Int,
              val kcal: Int,
              var uuid: String = "") : BaseViewItem(), Serializable

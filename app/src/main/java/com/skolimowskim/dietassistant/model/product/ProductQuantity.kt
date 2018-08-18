package com.skolimowskim.dietassistant.model.product

import androidx.annotation.StringRes
import com.skolimowskim.dietassistant.R

enum class ProductQuantity(@StringRes val title: Int) {
    PIECES(R.string.pieces),
    PACKAGES(R.string.packages),
    HANDFULS(R.string.handfuls),
    SPOONS(R.string.spoons),
//    MEAT(R.string.meats),
//    BREAD(R.string.breads),
    UNKNOWN(R.string.unknown),
}

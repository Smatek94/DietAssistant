package com.skolimowskim.dietassistant.view.products.manage

import androidx.annotation.StringRes
import com.skolimowskim.dietassistant.R

enum class ProductCategory(@StringRes val title: Int) {
    VEGETABLES(R.string.vegetables),
    FRUITS(R.string.fruits),
    NUTS(R.string.nuts),
    CARBOS(R.string.carbos),
    MEAT(R.string.meats),
    BREAD(R.string.breads),
    UNKNOWN(R.string.unknown),
}

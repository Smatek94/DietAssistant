package com.skolimowskim.dietassistant.view.products

import com.skolimowskim.dietassistant.model.product.ProductsRepo
import com.skolimowskim.dietassistant.model.product.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsViewModel(private val productsRepo : ProductsRepo) {

    fun getProducts(): Observable<ArrayList<Product>> {
        return productsRepo.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

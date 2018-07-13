package com.skolimowskim.dietassistant.view.products

import com.skolimowskim.dietassistant.model.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsViewModel {

    fun getProducts(): Observable<ArrayList<Product>> {
        val arrayList = ArrayList<Product>()
        arrayList.add(Product())
        return Observable.just(arrayList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

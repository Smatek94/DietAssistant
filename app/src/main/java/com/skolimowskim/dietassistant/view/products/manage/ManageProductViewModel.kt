package com.skolimowskim.dietassistant.view.products.manage

import com.skolimowskim.dietassistant.CacheResponse
import com.skolimowskim.dietassistant.model.product.ProductsRepo
import com.skolimowskim.dietassistant.model.product.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ManageProductViewModel(private val productsRepo : ProductsRepo) {

    fun addProduct(product: Product): Observable<CacheResponse> {
        return productsRepo.saveProductToCache(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateProduct(product: Product): Observable<CacheResponse> {
        return productsRepo.updateProductInCache(product)
                .delay(10,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteProduct(productUuid: String?): Observable<CacheResponse> {
        return if(productUuid != null)
            productsRepo.deleteProductInCache(productUuid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        else
            Observable.empty()
    }


}

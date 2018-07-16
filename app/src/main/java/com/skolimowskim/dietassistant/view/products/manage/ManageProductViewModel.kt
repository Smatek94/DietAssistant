package com.skolimowskim.dietassistant.view.products.manage

import com.skolimowskim.dietassistant.CacheResponse
import com.skolimowskim.dietassistant.model.product.ProductsRepo
import com.skolimowskim.dietassistant.model.product.Product
import io.reactivex.Observable

class ManageProductViewModel(private val productsRepo : ProductsRepo) {

    fun addProduct(product: Product): Observable<CacheResponse> {
        return productsRepo.saveProductToCache(product)
    }

    fun updateProduct(product: Product): Observable<CacheResponse> {
        return productsRepo.updateProductInCache(product)
    }

    fun deleteProduct(productUuid: String?): Observable<CacheResponse> {
        return if(productUuid != null)
            productsRepo.deleteProductInCache(productUuid)
        else
            Observable.empty()
    }


}

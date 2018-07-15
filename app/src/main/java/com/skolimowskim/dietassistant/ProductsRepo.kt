package com.skolimowskim.dietassistant

import android.content.SharedPreferences
import com.google.gson.Gson
import com.skolimowskim.dietassistant.dagger.RepoModule
import com.skolimowskim.dietassistant.model.Product
import io.reactivex.Observable
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ProductsRepo(val sharedPreferences: SharedPreferences, val gson: Gson) {

    fun getProducts(): Observable<ArrayList<Product>> {
        return Observable.just(getProductsFromCache())
    }

    fun saveProductToCache(product: Product): Observable<CacheResponse> {
        val productsFromCache = getProductsFromCache()
        product.uuid = generateRandomUuid(productsFromCache)
        productsFromCache.add(product)
        sharedPreferences.edit().putString(RepoModule.PRODUCTS, gson.toJson(productsFromCache)).apply()
        return Observable.just(CacheResponse.SUCCESS)
    }

    private fun generateRandomUuid(productsFromCache: ArrayList<Product>): String {
        var uuid: String
        do {
            uuid = UUID.randomUUID().toString()
        } while (isUuidInCache(uuid, productsFromCache))
        return uuid
    }

    private fun isUuidInCache(uuid: String, productsFromCache: ArrayList<Product>): Boolean {
        productsFromCache.forEach{
            if(it.uuid == uuid) return true
        }
        return false
    }

    fun updateProductInCache(product: Product): Observable<CacheResponse> {
        val productsFromCache = getProductsFromCache()
        updateProductInList(product, productsFromCache)
        sharedPreferences.edit().putString(RepoModule.PRODUCTS, gson.toJson(productsFromCache)).apply()
        return Observable.just(CacheResponse.SUCCESS)
    }

    private fun updateProductInList(product: Product, productsFromCache: ArrayList<Product>) {
        val iterator = productsFromCache.listIterator()
        while (iterator.hasNext()){
            val it = iterator.next()
            if(it.uuid == product.uuid) {
                iterator.set(product)
                return
            }
        }
    }

    private fun getProductsFromCache(): ArrayList<Product> {
        val productsString = sharedPreferences.getString(RepoModule.PRODUCTS, "")
        val productsType = object : TypeToken<ArrayList<Product>>() {}.type
        return gson.fromJson<ArrayList<Product>>(productsString, productsType) ?: ArrayList()
    }
}
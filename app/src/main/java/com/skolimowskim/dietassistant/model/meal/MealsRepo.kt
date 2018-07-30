package com.skolimowskim.dietassistant.model.meal

import android.content.SharedPreferences
import com.google.gson.Gson
import com.skolimowskim.dietassistant.dagger.RepoModule
import io.reactivex.Observable
import com.google.gson.reflect.TypeToken
import com.skolimowskim.dietassistant.CacheResponse
import com.skolimowskim.dietassistant.model.product.Product
import java.util.*
import kotlin.collections.ArrayList

class MealsRepo(private val sharedPreferences: SharedPreferences, private val gson: Gson) {

    fun getMeals(): Observable<ArrayList<Meal>> {
        return Observable.just(getMealsFromCache())
    }

    private fun getMealsFromCache(): ArrayList<Meal> {
        val mealsString = sharedPreferences.getString(RepoModule.MEALS, "")
        val mealsType = object : TypeToken<ArrayList<Meal>>() {}.type
        return gson.fromJson<ArrayList<Meal>>(mealsString, mealsType) ?: ArrayList()
    }

    fun saveMealToCache(meal: Meal): Observable<CacheResponse> {
        val mealsFromCache = getMealsFromCache()
        meal.uuid = generateRandomUuid(mealsFromCache)
        mealsFromCache.add(meal)
        sharedPreferences.edit().putString(RepoModule.MEALS, gson.toJson(mealsFromCache)).apply()
        return Observable.just(CacheResponse.SUCCESS)
    }

    fun updateMealInCache(meal: Meal): Observable<CacheResponse> {
        val mealsFromCache = getMealsFromCache()
        updateMealInList(meal, mealsFromCache)
        sharedPreferences.edit().putString(RepoModule.MEALS, gson.toJson(mealsFromCache)).apply()
        return Observable.just(CacheResponse.SUCCESS)
    }

    fun deleteMealInCache(mealUuid: String): Observable<CacheResponse> {
        val mealsFromCache = getMealsFromCache()
        deleteMealInList(mealUuid, mealsFromCache)
        sharedPreferences.edit().putString(RepoModule.MEALS, gson.toJson(mealsFromCache)).apply()
        return Observable.just(CacheResponse.SUCCESS)
    }

    private fun updateMealInList(meal: Meal, mealsFromCache: ArrayList<Meal>) {
        val iterator = mealsFromCache.listIterator()
        while (iterator.hasNext()){
            val it = iterator.next()
            if(it.uuid == meal.uuid) {
                iterator.set(meal)
                return
            }
        }
    }

    private fun deleteMealInList(mealUuid: String, mealsFromCache: ArrayList<Meal>) {
        val iterator = mealsFromCache.listIterator()
        while (iterator.hasNext()){
            val it = iterator.next()
            if(it.uuid == mealUuid) {
                iterator.remove()
                return
            }
        }
    }

    private fun generateRandomUuid(mealsFromCache: ArrayList<Meal>): String {
        var uuid: String
        do {
            uuid = UUID.randomUUID().toString()
        } while (isUuidInCache(uuid, mealsFromCache))
        return uuid
    }

    private fun isUuidInCache(uuid: String, mealsFromCache: ArrayList<Meal>): Boolean {
        mealsFromCache.forEach{
            if(it.uuid == uuid) return true
        }
        return false
    }

}
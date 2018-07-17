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

class MealsRepo(val sharedPreferences: SharedPreferences, val gson: Gson) {

    fun getMeals(): Observable<ArrayList<Meal>> {
        return Observable.just(getMealsFromCache())
    }

    private fun getMealsFromCache(): ArrayList<Meal> {
        val mealsString = sharedPreferences.getString(RepoModule.MEALS, "")
        val mealsType = object : TypeToken<ArrayList<Meal>>() {}.type
        return gson.fromJson<ArrayList<Meal>>(mealsString, mealsType) ?: ArrayList()
    }

    fun saveMealToCache(meal: Meal): Observable<CacheResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateMealInCache(meal: Meal): Observable<CacheResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteMealInCache(mealUuid: String): Observable<CacheResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
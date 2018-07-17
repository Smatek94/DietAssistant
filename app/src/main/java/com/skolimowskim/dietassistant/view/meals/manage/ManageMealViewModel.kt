package com.skolimowskim.dietassistant.view.meals.manage

import com.skolimowskim.dietassistant.CacheResponse
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.model.meal.MealsRepo
import io.reactivex.Observable

class ManageMealViewModel(private val mealsRepo: MealsRepo) {

    fun addMeal(meal: Meal): Observable<CacheResponse> {
        return mealsRepo.saveMealToCache(meal)
    }

    fun updateMeal(meal: Meal): Observable<CacheResponse> {
        return mealsRepo.updateMealInCache(meal)
    }

    fun deleteMeal(mealUuid: String?): Observable<CacheResponse> {
        return if (mealUuid != null)
            mealsRepo.deleteMealInCache(mealUuid)
        else
            Observable.empty()
    }


}

package com.skolimowskim.dietassistant.view.meals

import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.model.meal.MealsRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MealsViewModel(private val mealsRepo: MealsRepo) {

    fun getMeals(): Observable<ArrayList<Meal>> {
        return mealsRepo.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

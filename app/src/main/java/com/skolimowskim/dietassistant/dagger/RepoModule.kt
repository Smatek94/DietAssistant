package com.skolimowskim.dietassistant.dagger

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.skolimowskim.dietassistant.model.meal.MealsRepo
import com.skolimowskim.dietassistant.model.product.ProductsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    private val PREFS_NAME: String = "prefs_diet_assistant"
    companion object {
        const val PRODUCTS: String = "diet_assistant_products_key"
        const val MEALS: String = "diet_assistant_meals_key"
    }

    @Provides
    @Singleton
    internal fun provideSharedPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideProductsRepo(sharedPreferences: SharedPreferences, gson: Gson) = ProductsRepo(sharedPreferences, gson)

    @Provides
    @Singleton
    internal fun provideMealsRepo(sharedPreferences: SharedPreferences, gson: Gson) = MealsRepo(sharedPreferences, gson)
}
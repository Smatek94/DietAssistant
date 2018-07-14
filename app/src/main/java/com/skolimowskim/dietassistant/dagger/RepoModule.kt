package com.skolimowskim.dietassistant.dagger

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skolimowskim.dietassistant.ProductsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    private val PREFS_NAME: String = "prefs_diet_assistant"
    companion object {
        const val PRODUCTS: String = "diet_assistant_products_key"
    }

    @Provides
    @Singleton
    internal fun provideSharedPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideProductsRepo(sharedPreferences: SharedPreferences, gson: Gson) = ProductsRepo(sharedPreferences, gson)
}
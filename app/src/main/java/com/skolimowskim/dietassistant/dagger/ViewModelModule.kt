package com.skolimowskim.dietassistant.dagger

import com.skolimowskim.dietassistant.model.meal.MealsRepo
import com.skolimowskim.dietassistant.model.product.ProductsRepo
import com.skolimowskim.dietassistant.view.meals.MealsViewModel
import com.skolimowskim.dietassistant.view.products.ProductsViewModel
import com.skolimowskim.dietassistant.view.products.manage.ManageProductViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    internal fun provideProductsViewModel(productsRepo: ProductsRepo) = ProductsViewModel(productsRepo)

    @Provides
    @Singleton
    internal fun provideManageProductViewModel(productsRepo: ProductsRepo) = ManageProductViewModel(productsRepo)

    @Provides
    @Singleton
    internal fun provideMealsViewModel(mealsRepo: MealsRepo) = MealsViewModel(mealsRepo)
}
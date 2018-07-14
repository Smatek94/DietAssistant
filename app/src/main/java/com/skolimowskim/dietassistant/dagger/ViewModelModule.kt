package com.skolimowskim.dietassistant.dagger

import com.skolimowskim.dietassistant.ProductsRepo
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
}
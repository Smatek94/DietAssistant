package com.skolimowskim.dietassistant.dagger

import com.skolimowskim.dietassistant.view.products.ProductsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    internal fun provideProductsViewModel() = ProductsViewModel()
}
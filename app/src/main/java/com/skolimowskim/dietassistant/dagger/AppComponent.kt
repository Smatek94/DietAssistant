package com.skolimowskim.dietassistant.dagger

import com.skolimowskim.dietassistant.view.products.ProductsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    (ConfigModule::class),
//    (MainModule::class),
//    (NetModule::class),
    (ViewModelModule::class)])
interface AppComponent {
    fun inject(productsActivity: ProductsActivity)
}
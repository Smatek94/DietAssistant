package com.skolimowskim.dietassistant.dagger

import com.skolimowskim.dietassistant.view.products.ProductsActivity
import com.skolimowskim.dietassistant.view.products.manage.ManageProductActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    (ConfigModule::class),
    (MainModule::class),
    (RepoModule::class),
    (ViewModelModule::class)])
interface AppComponent {
    fun inject(productsActivity: ProductsActivity)
    fun inject(productsActivity: ManageProductActivity)
}
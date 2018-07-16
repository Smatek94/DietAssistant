package com.skolimowskim.dietassistant.view.meals

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.util.DisposableHelper
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.adapter.MealsAdapter
import com.skolimowskim.dietassistant.view.meals.manage.ManageMealActivity
import com.skolimowskim.dietassistant.view.products.ProductsActivity
import com.skolimowskim.dietassistant.view.products.manage.ManageProductActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject

class MealsActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MealsViewModel
    private lateinit var mealsAdapter: MealsAdapter

    private var disposable: Disposable? = null

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ProductsActivity::class.java)
    }

    // ********************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        (application as App).component.inject(this)

        mealsAdapter = MealsAdapter(LayoutInflater.from(this), object : OnItemSelectedListener<Meal> {
            override fun onItemSelected(item: Meal) {
                startActivity(ManageMealActivity.createIntent(this@MealsActivity, item))
            }
        })
        products_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        products_recycler.adapter = mealsAdapter

        fab.setOnClickListener {
            startActivity(ManageProductActivity.createIntent(this))
        }
    }

    override fun onStart() {
        super.onStart()
        getMeals()
    }

    private fun getMeals() {
        DisposableHelper.dispose(disposable)
        disposable = viewModel.getMeals()
                .doOnSubscribe {}
                .doFinally {}
                .subscribe({ onGetMealsSuccess(it) }, {})
    }

    private fun onGetMealsSuccess(products: ArrayList<Meal>) {
        mealsAdapter.updateMeals(products)
    }
}

package com.skolimowskim.dietassistant.view.meals

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skolimowskim.dietassistant.BaseActivity
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.util.DisposableHelper
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.adapter.MealsAdapter
import com.skolimowskim.dietassistant.view.meals.manage.ManageMealActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_meals.*
import javax.inject.Inject

class MealsActivity : BaseActivity() {

    @Inject lateinit var viewModel: MealsViewModel
    private lateinit var mealsAdapter: MealsAdapter

    private var disposable: Disposable? = null

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MealsActivity::class.java)
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
        meals_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        meals_recycler.adapter = mealsAdapter

        fab.setOnClickListener {
            startActivity(ManageMealActivity.createIntent(this))
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

    private fun onGetMealsSuccess(meals: ArrayList<Meal>) {
        mealsAdapter.updateMeals(meals)
    }
}

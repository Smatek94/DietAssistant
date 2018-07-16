package com.skolimowskim.dietassistant.view.meals.manage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.Meal

class ManageMealActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ManageMealActivity::class.java)

        private const val MEAL_EXTRA: String = "meal_extra"

        fun createIntent(context: Context, meal: Meal): Intent = Intent(context, ManageMealActivity::class.java)
                .putExtra(MEAL_EXTRA, meal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_meal)
    }
}

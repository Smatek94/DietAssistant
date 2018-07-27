package com.skolimowskim.dietassistant.view.meals.manage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.skolimowskim.dietassistant.BaseActivity
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.AppBarHelper
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.manage.adapter.MealProductsAdapter
import com.skolimowskim.dietassistant.view.meals.manage.adapter.OnProductWeightChangedListener
import com.skolimowskim.dietassistant.view.meals.manage.addProduct.AddProductActivity
import kotlinx.android.synthetic.main.activity_manage_meal.*
import javax.inject.Inject

class ManageMealActivity : BaseActivity() {

    @Inject lateinit var viewModel: ManageMealViewModel
    private lateinit var meal: Meal

    private lateinit var mealProductsAdapter: MealProductsAdapter
    @DrawableRes private var fabIcon: Int = 0

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ManageMealActivity::class.java)

        private const val MEAL_EXTRA: String = "meal_extra"
        const val SELECTED_PRODUCT: String = "selected_product"
        const val SELECT_PRODUCT_RESULT_CODE: Int = 952

        fun createIntent(context: Context, meal: Meal): Intent = Intent(context, ManageMealActivity::class.java)
                .putExtra(MEAL_EXTRA, meal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_meal)

        (application as App).component.inject(this)

        mealProductsAdapter = MealProductsAdapter(LayoutInflater.from(this), object : OnItemSelectedListener<ProductInMeal> {
            override fun onItemSelected(item: ProductInMeal) {

            }
        }, object : OnProductWeightChangedListener {
            override fun productWeightChanged(weight: Int) {
                // todo handle product weight change on ui and viewmodel
            }
        })
        meal_products_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        meal_products_recycler.adapter = mealProductsAdapter

        add_product.setOnClickListener { startActivityForResult(AddProductActivity.createIntent(this@ManageMealActivity), SELECT_PRODUCT_RESULT_CODE) }

        if (intent.extras != null) {
            meal = intent.extras.getSerializable(MEAL_EXTRA) as Meal
            AppBarHelper.setUpChildToolbar(this, R.string.update_meal)
            fabIcon = R.drawable.ic_update

            /* productUuid = product.uuid

             name_input.setText(product.name)
             carbo_input.setText(product.carbo.toString())
             protein_input.setText(product.protein.toString())
             fat_input.setText(product.fat.toString())

             manage_product.setText(R.string.update_product)
             manage_product.setOnClickListener { onUpdateClicked() }

             delete_product.visibility = View.VISIBLE
             delete_product.setOnClickListener { onDeleteButtonClicked() }

             category_spinner.setSelection(productCategorySpinnerAdapter.getItemPosition(product.productCategory))*/
        } else {
            meal = Meal()
            updateMealProducts()
            AppBarHelper.setUpChildToolbar(this, R.string.add_meal)
            fabIcon = R.drawable.ic_add
            /*manage_product.setText(R.string.add_product)
            manage_product.setOnClickListener { onAddClicked() }

            delete_product.visibility = View.GONE*/
        }
        changeFabIcon()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PRODUCT_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                meal.productList.add(ProductInMeal(data!!.getSerializableExtra(SELECTED_PRODUCT) as Product, 100))
                updateMealProducts()
            }
        }
    }

    private fun updateMealProducts() {
        mealProductsAdapter.updateProducts(meal.productList)
        var carbo = 0
        var protein = 0
        var fat = 0
        var kcal = 0
        var gram = 0
        meal.productList.forEach {
            carbo += it.product.carbo
            protein += it.product.protein
            fat += it.product.fat
            kcal += it.product.kcal
            gram += it.weight
        }
        carbo_text.text = carbo.toString()
        protein_text.text = protein.toString()
        fat_text.text = fat.toString()
        kcal_text.text = kcal.toString()
        gram_text.text = gram.toString()
    }

    private fun changeFabIcon() {
        manage_meal.setImageDrawable(ContextCompat.getDrawable(this, fabIcon))
    }
}

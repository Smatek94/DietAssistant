package com.skolimowskim.dietassistant.view.meals.manage

import android.app.Activity
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
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.manage.adapter.MealProductsAdapter
import com.skolimowskim.dietassistant.view.meals.manage.addProduct.AddProductActivity
import kotlinx.android.synthetic.main.activity_manage_meal.*
import javax.inject.Inject

class ManageMealActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: ManageMealViewModel

    private lateinit var mealProductsAdapter: MealProductsAdapter

    private lateinit var meal: Meal

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

        mealProductsAdapter = MealProductsAdapter(LayoutInflater.from(this), object : OnAddProductClicked {
            override fun onAddProductClicked() {
                startActivityForResult(AddProductActivity.createIntent(this@ManageMealActivity), SELECT_PRODUCT_RESULT_CODE)
            }
        }, object : OnItemSelectedListener<Product> {
            override fun onItemSelected(item: Product) {

            }
        })
        meal_products_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        meal_products_recycler.adapter = mealProductsAdapter

        if (intent.extras != null) {
            meal = intent.extras.getSerializable(MEAL_EXTRA) as Meal

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
            mealProductsAdapter.updateProducts(meal.productList)
            /*manage_product.setText(R.string.add_product)
            manage_product.setOnClickListener { onAddClicked() }

            delete_product.visibility = View.GONE*/
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PRODUCT_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                meal.productList.add(data!!.getSerializableExtra(SELECTED_PRODUCT) as Product)
                mealProductsAdapter.updateProducts(meal.productList)
            }
        }
    }
}

package com.skolimowskim.dietassistant.view.meals.manage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.babyassistant.ui.util.delete.DeleteDialog
import com.babyassistant.ui.util.delete.OnDeleteDialogListener
import com.skolimowskim.dietassistant.BaseActivity
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.AppBarHelper
import com.skolimowskim.dietassistant.util.DialogUtils
import com.skolimowskim.dietassistant.util.DisposableHelper
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.manage.adapter.MealProductsAdapter
import com.skolimowskim.dietassistant.view.meals.manage.adapter.OnProductWeightChangedListener
import com.skolimowskim.dietassistant.view.meals.manage.addProduct.AddProductActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_manage_meal.*
import kotlinx.android.synthetic.main.item_meal.*
import javax.inject.Inject

class ManageMealActivity : BaseActivity(), OnDeleteDialogListener {

    @Inject lateinit var viewModel: ManageMealViewModel
    private lateinit var meal: Meal

    private lateinit var mealProductsAdapter: MealProductsAdapter
    @DrawableRes private var fabIcon: Int = 0

    private var disposable: Disposable? = null

    private var isUpdate: Boolean = false

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
            override fun productWeightChanged(weight: Int, position: Int) {
                meal.productList[position].weight = weight
                updateAllProductsMacro()
            }
        })
        meal_products_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        meal_products_recycler.adapter = mealProductsAdapter

        add_product.setOnClickListener { startActivityForResult(AddProductActivity.createIntent(this@ManageMealActivity), SELECT_PRODUCT_RESULT_CODE) }

        if (intent.extras != null) {
            meal = intent.extras.getSerializable(MEAL_EXTRA) as Meal
            AppBarHelper.setUpChildToolbar(this, R.string.update_meal)
            fabIcon = R.drawable.ic_update
            manage_meal.setOnClickListener { onUpdateClicked() }
            isUpdate = true
            name_input.setText(meal.name)
        } else {
            meal = Meal()
            AppBarHelper.setUpChildToolbar(this, R.string.add_meal)
            fabIcon = R.drawable.ic_add
            manage_meal.setOnClickListener { onAddClicked() }
            isUpdate = false
        }
        updateMealProducts()
        changeFabIcon()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.manage_meal_menu, menu)
        if (!isUpdate) {
            val findItem = menu!!.findItem(R.id.delete)
            findItem.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.delete) {
            onDeleteButtonClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // ********************************************************************************************************************************

    private lateinit var deleteDialog: DeleteDialog

    private fun onDeleteButtonClicked() {
        deleteDialog = DialogUtils.showDeleteDialog(getString(R.string.delete_product), supportFragmentManager)
    }

    override fun onDeleteClicked() {
        DisposableHelper.dispose(disposable)
        disposable = viewModel.deleteMeal(meal.uuid)
                .doOnSubscribe { deleteDialog.toggleLoading(true) }
                .doFinally { deleteDialog.toggleLoading(false) }
                .subscribe({ finish() }, { onDeleteFail(it) })

    }

    private fun onDeleteFail(it: Throwable) {
        TODO("not implemented")
    }

    override fun onCancelClicked() {
        deleteDialog.dismiss()
    }

    // ********************************************************************************************************************************

    private fun onAddClicked() {
        meal.name = name_input.text.toString()
        DisposableHelper.dispose(disposable)
        disposable = viewModel.addMeal(meal)
                .doOnSubscribe { }
                .doFinally { }
                .subscribe({ finish() }, { onManageFail(it) })
    }

    private fun onUpdateClicked() {
        meal.name = name_input.text.toString()
        DisposableHelper.dispose(disposable)
        disposable = viewModel.updateMeal(meal)
                .doOnSubscribe { }
                .doFinally { }
                .subscribe({ finish() }, { onManageFail(it) })
    }

    private fun onManageFail(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PRODUCT_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val productInMeal = ProductInMeal(data!!.getSerializableExtra(SELECTED_PRODUCT) as Product, 100)
                if (!meal.productList.contains(productInMeal)) {
                    meal.productList.add(productInMeal)
                    updateMealProducts()
                } else {
                    Toast.makeText(this, R.string.product_already_added, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateMealProducts() {
        mealProductsAdapter.updateProducts(meal.productList)
        updateAllProductsMacro()
    }

    private fun updateAllProductsMacro() {
        var carbo = 0
        var protein = 0
        var fat = 0
        var kcal = 0
        var gram = 0
        meal.productList.forEach {
            carbo += it.product.carbo * it.weight / 100
            protein += it.product.protein * it.weight / 100
            fat += it.product.fat * it.weight / 100
            kcal += it.product.kcal * it.weight / 100
            gram += it.weight
        }
        meal.carbo = carbo
        meal.protein = protein
        meal.fat = fat
        meal.kcal = kcal
        meal.gram = gram
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

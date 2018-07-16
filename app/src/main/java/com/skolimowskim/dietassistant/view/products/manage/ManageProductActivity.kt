package com.skolimowskim.dietassistant.view.products.manage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.babyassistant.ui.util.delete.DeleteDialog
import com.babyassistant.ui.util.delete.OnDeleteDialogListener
import com.google.android.material.chip.Chip
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.Product
import com.skolimowskim.dietassistant.util.DialogUtils
import com.skolimowskim.dietassistant.util.DisposableHelper
import com.skolimowskim.dietassistant.util.TextUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_manage_product.*
import java.util.*
import javax.inject.Inject

class ManageProductActivity : AppCompatActivity(), OnDeleteDialogListener {

    @Inject lateinit var viewModel: ManageProductViewModel

    private var isUpdate: Boolean = false

    private lateinit var product: Product
    private var productUuid: String? = null

    private var disposable: Disposable? = null

    private lateinit var productCategorySpinnerAdapter: ProductCategorySpinnerAdapter

    private val kcalTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            val carbo = TextUtils.getIntValueOfText(carbo_input)
            val protein = TextUtils.getIntValueOfText(protein_input)
            val fat = TextUtils.getIntValueOfText(fat_input)

            kcal_input.setText("" + ((carbo * 4) + (protein * 4) + (fat * 9)))
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    companion object {
        private const val PRODUCT_EXTRA_KEY: String = "PRODUCT_KEY"

        fun createIntent(context: Context): Intent = Intent(context, ManageProductActivity::class.java)

        fun createIntent(context: Context, product: Product): Intent = Intent(context, ManageProductActivity::class.java)
                .putExtra(PRODUCT_EXTRA_KEY, product)
    }

    // ********************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)

        (application as App).component.inject(this)

        carbo_input.addTextChangedListener(kcalTextWatcher)
        protein_input.addTextChangedListener(kcalTextWatcher)
        fat_input.addTextChangedListener(kcalTextWatcher)

        productCategorySpinnerAdapter = ProductCategorySpinnerAdapter(this)
        category_spinner.adapter = productCategorySpinnerAdapter

        if (intent.extras != null) {
            isUpdate = true
            product = intent.extras.getSerializable(PRODUCT_EXTRA_KEY) as Product

            productUuid = product.uuid

            name_input.editText!!.setText(product.name)
            carbo_input.setText(product.carbo.toString())
            protein_input.setText(product.protein.toString())
            fat_input.setText(product.fat.toString())

            manage_product.setText(R.string.update_product)
            manage_product.setOnClickListener { onUpdateClicked() }

            delete_product.visibility = View.VISIBLE
            delete_product.setOnClickListener { onDeleteButtonClicked() }

//            for(i in 0..category_spinner.childCount){
//                if(category_spinner.getChildAt(i) == product.productCategory){
//                    category_spinner.setSelection(i)
//                }
//            }
        } else {
            manage_product.setText(R.string.add_product)
            manage_product.setOnClickListener { onAddClicked() }

            delete_product.visibility = View.GONE
        }
    }

    // ********************************************************************************************************************************

    private fun onAddClicked() {
        val productFromInputs = getProductFromInputs()
        if (productFromInputs != null) {
            DisposableHelper.dispose(disposable)
            disposable = viewModel.addProduct(productFromInputs)
                    .doOnSubscribe { toggleLoading(true) }
                    .doFinally { toggleLoading(false) }
                    .subscribe({ finish() }, { onManageFail(it) })

        }
    }

    private fun onUpdateClicked() {
        val productFromInputs = getProductFromInputs()
        if (productFromInputs != null) {
            DisposableHelper.dispose(disposable)
            disposable = viewModel.updateProduct(productFromInputs)
                    .doOnSubscribe { toggleLoading(true) }
                    .doFinally { toggleLoading(false) }
                    .subscribe({ finish() }, { onManageFail(it) })
        }
    }

    private fun onManageFail(it: Throwable) {
        TODO("not implemented")
    }

    // ********************************************************************************************************************************

    private lateinit var deleteDialog: DeleteDialog

    private fun onDeleteButtonClicked() {
        deleteDialog = DialogUtils.showDeleteDialog(getString(R.string.delete_product), supportFragmentManager)
    }

    override fun onDeleteClicked() {
        DisposableHelper.dispose(disposable)
        disposable = viewModel.deleteProduct(productUuid)
                .doOnSubscribe { toggleLoading(true) }
                .doFinally { toggleLoading(false) }
                .subscribe({ finish() }, { onDeleteFail(it) })

    }

    private fun onDeleteFail(it: Throwable) {
        TODO("not implemented")
    }

    override fun onCancelClicked() {
        deleteDialog.dismiss()
    }

    // ********************************************************************************************************************************

    private fun getProductFromInputs(): Product? {
        // todo validate
        val product = Product(name_input.editText!!.text.toString(),
                              TextUtils.getIntValueOfText(carbo_input),
                              TextUtils.getIntValueOfText(protein_input),
                              TextUtils.getIntValueOfText(fat_input),
                              TextUtils.getIntValueOfText(kcal_input),
                              category_spinner.selectedItem as ProductCategory)
        if (productUuid != null) {
            product.uuid = productUuid!!
        }
        return product

    }

    private fun toggleLoading(isLoading: Boolean) {
        if (isLoading) {
            manage_product_progress.visibility = View.VISIBLE
            manage_product.visibility = View.GONE
        } else {
            manage_product.visibility = View.VISIBLE
            manage_product_progress.visibility = View.GONE
        }
    }
}

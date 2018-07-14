package com.skolimowskim.dietassistant.view.products.manage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.Product
import kotlinx.android.synthetic.main.activity_manage_product.*
import javax.inject.Inject

class ManageProductActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ManageProductViewModel

    private var isUpdate: Boolean = false

    private lateinit var product: Product

    private val kcalTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            val carbo = if (carbo_input.text.toString() != "") carbo_input.text.toString().toInt() else 0
            val protein = if (protein_input.text.toString() != "") protein_input.text.toString().toInt() else 0
            val fat = if (fat_input.text.toString() != "") fat_input.text.toString().toInt() else 0

            kcal_text.text = "" + ((carbo * 4) + (protein * 4) + (fat * 9))
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)

        (application as App).component.inject(this)

        carbo_input.addTextChangedListener(kcalTextWatcher)
        protein_input.addTextChangedListener(kcalTextWatcher)
        fat_input.addTextChangedListener(kcalTextWatcher)

        if (intent.extras != null) {
            isUpdate = true
            product = intent.extras.getSerializable(PRODUCT_EXTRA_KEY) as Product

            carbo_input.setText(product.carbo.toString())
            protein_input.setText(product.protein.toString())
            fat_input.setText(product.fat.toString())

            manage_product.setText(R.string.update_product)
            manage_product.setOnClickListener { onUpdateClicked() }
        } else {
            manage_product.setText(R.string.add_product)
            manage_product.setOnClickListener { onAddClicked() }
        }
    }

    private fun onAddClicked() {
        val productFromInputs = getProductFromInputs()
        if (productFromInputs != null) viewModel.addProduct(productFromInputs)
                .doOnSubscribe { toggleLoading(true) }
                .doFinally { toggleLoading(false) }
                .subscribe({ finish() }, { onManageFail(it) })
    }

    private fun onManageFail(it: Throwable) {
        TODO("not implemented")
    }

    private fun onUpdateClicked() {
        val productFromInputs = getProductFromInputs()
        if (productFromInputs != null) viewModel.updateProduct(productFromInputs)
                .doOnSubscribe { toggleLoading(true) }
                .doFinally { toggleLoading(false) }
                .subscribe({ finish() }, { onManageFail(it) })
    }

    private fun getProductFromInputs(): Product? {
        // todo validate
        return Product("test", 10, 10, 10)
    }

    private fun toggleLoading(isLoading: Boolean) {
        if(isLoading) {
            manage_product_progress.visibility = View.VISIBLE
            manage_product.visibility = View.GONE
        } else {
            manage_product.visibility = View.VISIBLE
            manage_product_progress.visibility = View.GONE
        }
    }
}

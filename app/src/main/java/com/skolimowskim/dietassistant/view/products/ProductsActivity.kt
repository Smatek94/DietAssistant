package com.skolimowskim.dietassistant.view.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.app.App
import com.skolimowskim.dietassistant.model.Product
import com.skolimowskim.dietassistant.util.DisposableHelper
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.products.adapter.ProductsAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_products.*
import javax.inject.Inject

class ProductsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsAdapter

    private var disposable: Disposable? = null

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ProductsActivity::class.java)
    }

    // ********************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        (application as App).component.inject(this)

        productsAdapter = ProductsAdapter(LayoutInflater.from(this), object : OnItemSelectedListener<Product> {
            override fun onItemSelected(item: Product) {
                //                this@TaskEntriesActivity.onTaskEntrySelected(item)
                // todo start manage product activity to edit product
            }
        })
        products_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        products_recycler.adapter = productsAdapter

        fab.setOnClickListener {
            // todo start manage product acitivty to add new product
        }
    }

    override fun onStart() {
        super.onStart()
        getProducts()
    }

    private fun getProducts() {
        DisposableHelper.dispose(disposable)
        disposable = viewModel.getProducts()
                .doOnSubscribe {}
                .doFinally {}
                .subscribe({ onGetProductsSuccess(it) }, {})
    }

    private fun onGetProductsSuccess(products: ArrayList<Product>) {
        productsAdapter.updateProducts(products)
    }
}

package com.skolimowskim.dietassistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skolimowskim.dietassistant.view.products.ProductsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(ProductsActivity.createIntent(this))
    }
}

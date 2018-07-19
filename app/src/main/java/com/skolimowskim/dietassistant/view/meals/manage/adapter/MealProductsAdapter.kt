package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.ProductInMeal
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.manage.OnAddProductClicked

class MealProductsAdapter(private val inflater: LayoutInflater,
                          private val listener: OnItemSelectedListener<ProductInMeal>) : RecyclerView.Adapter<BaseViewHolder<BaseViewItem>>() {


    private val products: ArrayList<BaseViewItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewItem> {
        val itemView = inflater.inflate(R.layout.item_product_added_to_meal, parent, false)
        return ProductAddedToMealViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewItem>, position: Int) {
        holder.populate(products[position])
    }

    fun updateProducts(products: ArrayList<ProductInMeal>){
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}

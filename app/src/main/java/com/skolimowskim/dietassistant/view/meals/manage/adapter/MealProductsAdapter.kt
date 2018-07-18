package com.skolimowskim.dietassistant.view.meals.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener
import com.skolimowskim.dietassistant.view.meals.manage.OnAddProductClicked

class MealProductsAdapter(private val inflater: LayoutInflater,
                          private val onAddProductClickedListener: OnAddProductClicked,
                          private val listener: OnItemSelectedListener<Product>) : RecyclerView.Adapter<BaseViewHolder<BaseViewItem>>() {

    companion object {
        const val HEADER : Int = 0
        const val ITEM : Int = 1
    }

    private val products: ArrayList<BaseViewItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewItem> {
        if(viewType == HEADER){
            val itemView = inflater.inflate(R.layout.item_add_product_to_meal_header, parent, false)
            return AddProductToMealHeaderViewHolder(itemView, onAddProductClickedListener)
        }
        val itemView = inflater.inflate(R.layout.item_product_added_to_meal, parent, false)
        return ProductAddedToMealViewHolder(itemView, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            HEADER
        } else {
            ITEM
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewItem>, position: Int) {
        holder.populate(products[position])
    }

    fun updateProducts(products: ArrayList<Product>){
        this.products.clear()
        this.products.add(AddProductToMealHeader())
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}

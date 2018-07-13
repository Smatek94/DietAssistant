package com.skolimowskim.dietassistant.view.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.model.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener

class ProductsAdapter(private val inflater: LayoutInflater, private val listener: OnItemSelectedListener<Product>) : RecyclerView.Adapter<BaseViewHolder<BaseViewItem>>() {

    private val products: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewItem> {
        val itemView = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView, listener)
    }

//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewItem>, position: Int) {
        holder.populate(products[position])
    }

    fun updateProducts(products: ArrayList<Product>){
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}

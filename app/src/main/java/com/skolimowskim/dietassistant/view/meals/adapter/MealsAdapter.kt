package com.skolimowskim.dietassistant.view.meals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.model.meal.Meal
import com.skolimowskim.dietassistant.util.recycler.BaseViewHolder
import com.skolimowskim.dietassistant.util.recycler.BaseViewItem
import com.skolimowskim.dietassistant.model.product.Product
import com.skolimowskim.dietassistant.util.OnItemSelectedListener

class MealsAdapter(private val inflater: LayoutInflater, private val listener: OnItemSelectedListener<Meal>) : RecyclerView.Adapter<BaseViewHolder<BaseViewItem>>() {

    private val meals: ArrayList<Meal> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewItem> {
        val itemView = inflater.inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(itemView, listener)
    }

//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewItem>, position: Int) {
        holder.populate(meals[position])
    }

    fun updateMeals(products: ArrayList<Meal>){
        this.meals.clear()
        this.meals.addAll(products)
        notifyDataSetChanged()
    }

}

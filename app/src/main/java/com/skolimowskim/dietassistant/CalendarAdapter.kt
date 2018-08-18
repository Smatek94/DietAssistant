package com.skolimowskim.dietassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CalendarAdapter(private val inflater: LayoutInflater) : BaseAdapter() {

    private val items: ArrayList<CalendarItem> = ArrayList()

    override fun getView(postiion: Int, convertView: View?, parentView: ViewGroup?): View {
        val itemView = convertView ?: inflater.inflate(R.layout.item_calendar, parentView, false)
        val test = itemView.findViewById<TextView>(R.id.test)
        test.text = "" + postiion
        return itemView
    }

    override fun getItem(p0: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = items.size

    fun updateItems(items: ArrayList<CalendarItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

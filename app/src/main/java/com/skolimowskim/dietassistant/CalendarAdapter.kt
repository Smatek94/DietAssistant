package com.skolimowskim.dietassistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class CalendarAdapter(private val inflater: LayoutInflater, private val listener: OnCalendarItemClickedListener) : BaseAdapter() {

    private val items: ArrayList<CalendarItem> = ArrayList()

    override fun getView(postiion: Int, convertView: View?, parentView: ViewGroup?): View {
        val itemView = convertView ?: inflater.inflate(R.layout.item_calendar, parentView, false)
        val test = itemView.findViewById<TextView>(R.id.test)
        val calendarItem = items[postiion]//
        val instance = Calendar.getInstance()
        instance.time = calendarItem.time
        test.text = instance.get(Calendar.DAY_OF_MONTH).toString()
        if (!calendarItem.sameMonth) test.setBackgroundColor(itemView.context.getColor(R.color.gray_calendar))
        else test.setBackgroundColor(itemView.context.getColor(R.color.text_white))
        itemView.setOnClickListener { listener.onCalendarItemClicked(postiion) }
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

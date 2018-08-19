package com.skolimowskim.dietassistant

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.skolimowskim.dietassistant.view.meals.MealsActivity
import com.skolimowskim.dietassistant.view.products.ProductsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendarAdapter: CalendarAdapter
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_launcher_background)
        }

        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer_layout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_meals -> {
                    startActivity(MealsActivity.createIntent(this))
                }
                R.id.nav_products -> {
                    startActivity(ProductsActivity.createIntent(this))
                }
            }
            true
        }

        initCalendarHeaders()
        calendarAdapter = CalendarAdapter(LayoutInflater.from(this))
        calendar_grid.adapter = calendarAdapter

        updateCalendar()
        calendar_next_button.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            updateCalendar()
        }
        calendar_prev_button.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            updateCalendar()
        }
    }

    private fun initCalendarHeaders() {
        val stringArray = resources.getStringArray(R.array.days_prefixes)
        stringArray.asList().forEach { calendar_header.addView(createDayHeaderTextView(it)) }
    }

    private fun createDayHeaderTextView(dayPrefix: String): View {
        val textView = TextView(this)
        textView.text = dayPrefix
        val layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.weight = 1.0f
        layoutParams.gravity = Gravity.CENTER
        textView.layoutParams = layoutParams
        textView.gravity = Gravity.CENTER
        val padding = resources.getDimensionPixelSize(R.dimen.space_content)
        textView.setPadding(padding, padding, padding, padding)
        return textView
    }

    private fun updateCalendar() {
        val cells = ArrayList<CalendarItem>()
        val calendar = Calendar.getInstance()
        calendar.time = this.calendar.time

        // set calendar to start of month
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        // find previous monday to start calendar from this date
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) calendar.add(Calendar.DAY_OF_YEAR, -1)

        while (cells.size < 42) { // month can be 6 rows (1 day in first, 28 in 2-5, 1-2 day in last)
            val isSameMonth = calendar.get(Calendar.MONTH) == this.calendar.get(Calendar.MONTH)
            // if cells size is already for 5 rows and month in current calendar
            // is different than global calendar then stop adding whole row only
            // for next month
            if(cells.size == 34 && !isSameMonth) {
                cells.add(CalendarItem(calendar.time, isSameMonth))
                break
            }
            if(cells.size == 35 && !isSameMonth) break
            cells.add(CalendarItem(calendar.time, isSameMonth))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        val sdf = SimpleDateFormat("MMM yyyy")
        calendar_date_display.text = sdf.format(this.calendar.time)

        calendarAdapter.updateItems(cells)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

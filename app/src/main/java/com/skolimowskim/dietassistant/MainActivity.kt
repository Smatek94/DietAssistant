package com.skolimowskim.dietassistant

import android.os.Bundle
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
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendarAdapter: CalendarAdapter

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
        return textView
    }

    private fun updateCalendar() {
        val cells = ArrayList<CalendarItem>()
        val calendar = Calendar.getInstance()

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        while (cells.size < 42) {
            cells.add(CalendarItem(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarAdapter.updateItems(cells)
        //        val sdf = SimpleDateFormat("MMM yyyy")
        //        txtDate.setText(sdf.format(currentDate.getTime()))
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

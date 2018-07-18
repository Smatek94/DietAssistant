package com.skolimowskim.dietassistant.util

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.skolimowskim.dietassistant.R

object AppBarHelper {

    fun setUpToolbar(activity: AppCompatActivity, @StringRes title: Int = R.string.app_name): Toolbar {
        val toolbar = getToolbar(activity)
        toolbar.setTitle(title)
        activity.setSupportActionBar(toolbar)
        return toolbar
    }

    fun setUpChildToolbar(activity: AppCompatActivity) {
        setActionBar(activity, R.drawable.ic_arrow_back)
    }

    fun setUpChildToolbar(activity: AppCompatActivity, title: String?) {
        setActionBar(activity, R.drawable.ic_arrow_back, title)
    }

    fun setUpChildToolbar(activity: AppCompatActivity, @StringRes title: Int) {
        setActionBar(activity, R.drawable.ic_arrow_back, activity.getString(title))
    }

    fun setUpChildToolbar(activity: AppCompatActivity, @StringRes title: Int, @ColorRes color: Int) {
        setActionBar(activity, R.drawable.ic_arrow_back, activity.getString(title), color)
    }

    fun updateToolbar(activity: AppCompatActivity, @StringRes title: Int) {
        val toolbar = getSupportToolbar(activity)
        toolbar.title = activity.getString(title)
        setDefaultToolbarColor(activity, toolbar)
    }

    private fun setDefaultToolbarColor(activity: AppCompatActivity, toolbar: Toolbar) {
        val color = ContextCompat.getColor(activity, R.color.colorPrimary)
        toolbar.setBackgroundColor(color)
        val window = activity.window
        window.statusBarColor = color
        window.navigationBarColor = color

    }

    fun updateToolbarColor(activity: AppCompatActivity, evaluatedColor: Int) {
        val toolbar = getToolbar(activity)
        toolbar.setBackgroundColor(evaluatedColor)
        activity.setSupportActionBar(toolbar)
    }

    fun updateToolbarTitle(activity: AppCompatActivity, title: Int) {
        val toolbar = getToolbar(activity)
        toolbar.setTitle(title)
        activity.setSupportActionBar(toolbar)
    }

    fun updateToolbarTitle(activity: AppCompatActivity, title: String) {
        val toolbar = getToolbar(activity)
        toolbar.title = title
        activity.setSupportActionBar(toolbar)
    }

    /*fun setUpCollapsingToolbar(activity: AppCompatActivity, title: String?) {
        val collapsingToolbarLayout = activity.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = title
    }

    fun setUpCollapsingToolbar(activity: AppCompatActivity, @StringRes title: Int) {
        val collapsingToolbarLayout = activity.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = activity.getString(title)
    }*/

    // ****************************************************************************************************************************************

    private fun getToolbar(activity: AppCompatActivity): Toolbar {
        return activity.findViewById(R.id.toolbar)
    }

    private fun getSupportToolbar(activity: AppCompatActivity): Toolbar {
        val toolbar = getToolbar(activity)
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        return toolbar
    }

    // ****************************************************************************************************************************************

    private fun setActionBar(activity: AppCompatActivity, @DrawableRes icon: Int) {
        val toolbar = createToolbar(activity, icon)
        activity.setSupportActionBar(toolbar)
    }

    private fun setActionBar(activity: AppCompatActivity, @DrawableRes icon: Int, title: String?) {
        val toolbar = createToolbar(activity, icon)
        if (title != null) toolbar.title = title // set title must be before setSupportActionBar
        activity.setSupportActionBar(toolbar)
    }

    private fun setActionBar(activity: AppCompatActivity, @DrawableRes icon: Int, title: String?, @ColorRes color: Int) {
        val toolbar = createToolbar(activity, icon)
        if (title != null) toolbar.title = title // set title must be before setSupportActionBar
        toolbar.setBackgroundColor(ContextCompat.getColor(activity, color))
        activity.setSupportActionBar(toolbar)
    }

    private fun createToolbar(activity: AppCompatActivity, @DrawableRes icon: Int): Toolbar {
        val toolbar = getSupportToolbar(activity)
        toolbar.setNavigationIcon(icon)
        return toolbar
    }

    /*
        private fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }
    */

}
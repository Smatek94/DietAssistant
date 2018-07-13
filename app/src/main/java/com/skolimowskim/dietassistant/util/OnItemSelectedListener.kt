package com.skolimowskim.dietassistant.util

interface OnItemSelectedListener<in T> {

    companion object {

        val NULL
            get() = object : OnItemSelectedListener<Any> {
                override fun onItemSelected(item: Any) {}
            }
    }

    // ****************************************************************************************************************************************

    fun onItemSelected(item: T)
}
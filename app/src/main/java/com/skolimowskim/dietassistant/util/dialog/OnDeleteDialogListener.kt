package com.babyassistant.ui.util.delete

interface OnDeleteDialogListener {

    companion object {
        val NULL: OnDeleteDialogListener = object : OnDeleteDialogListener {
            override fun onCancelClicked() {}

            override fun onDeleteClicked() {}
        }
    }

    // ****************************************************************************************************************************************

    fun onDeleteClicked()

    fun onCancelClicked()
}

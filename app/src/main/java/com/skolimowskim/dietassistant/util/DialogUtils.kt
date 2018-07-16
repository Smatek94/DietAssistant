package com.skolimowskim.dietassistant.util

import androidx.fragment.app.FragmentManager
import com.babyassistant.ui.util.delete.DeleteDialog

object DialogUtils {

    fun showDeleteDialog(title: String, supportFragmentManager: FragmentManager): DeleteDialog {
        val deleteDialog = DeleteDialog.newInstance(title)
        deleteDialog.show(supportFragmentManager, DeleteDialog::class.java.simpleName)
        return deleteDialog
    }

}
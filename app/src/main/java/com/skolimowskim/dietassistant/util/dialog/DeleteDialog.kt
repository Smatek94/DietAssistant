package com.babyassistant.ui.util.delete

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.skolimowskim.dietassistant.R
import com.skolimowskim.dietassistant.util.dialog.BaseDialog

class DeleteDialog : BaseDialog() {

    companion object {
        private const val DIALOG_TITLE: String = "dialog_title"

        fun newInstance(text: String): DeleteDialog {
            val deleteDialog = DeleteDialog()
            val bundle = Bundle()
            bundle.putString(DIALOG_TITLE, text)
            deleteDialog.arguments = bundle
            return deleteDialog
        }
    }

    private lateinit var dialogListener: OnDeleteDialogListener
    private lateinit var deleteButton: Button
    private lateinit var deleteProgress: ProgressBar

    // ****************************************************************************************************************************************

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnDeleteDialogListener) {
            dialogListener = context
        } else {
            throw Exception("Activity must implement OnDeleteDialogListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        dialogListener = OnDeleteDialogListener.NULL
    }

    // ****************************************************************************************************************************************

    override fun getLayout(): Int = R.layout.dialog_delete

    override fun isDialogCancelable() = true

    override fun setViewContent(context: Context?, view: View) {
        view.findViewById<TextView>(R.id.delete_text).text = arguments?.getString(DIALOG_TITLE)!!
        view.findViewById<Button>(R.id.cancel).setOnClickListener { dialogListener.onCancelClicked() }
        deleteProgress = view.findViewById(R.id.delete_progress)
        deleteButton = view.findViewById(R.id.delete_button)
        deleteButton.setOnClickListener { dialogListener.onDeleteClicked() }
    }

    // ****************************************************************************************************************************************

    fun toggleLoading(isLoading: Boolean) {
        if (isLoading) {
            deleteButton.visibility = GONE
            deleteProgress.visibility = VISIBLE
        } else {
            deleteButton.visibility = VISIBLE
            deleteProgress.visibility = GONE

        }
    }
}

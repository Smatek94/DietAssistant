package com.skolimowskim.dietassistant.util.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.skolimowskim.dietassistant.R

abstract class BaseDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(getLayout(), null)

        setViewContent(context, view)

        val dialog = AlertDialog.Builder(context!!, R.style.AppAlertDialogTheme)
                .setView(view)
                .setCancelable(isDialogCancelable())
                .create()
        dialog.setCanceledOnTouchOutside(isDialogCancelable())

        super.setCancelable(isDialogCancelable())
        return dialog
    }

    // ****************************************************************************************************************************************

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun isDialogCancelable(): Boolean

    protected abstract fun setViewContent(context: Context?, view: View)
}
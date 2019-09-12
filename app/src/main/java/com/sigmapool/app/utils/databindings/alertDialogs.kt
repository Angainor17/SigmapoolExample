package com.sigmapool.app.utils.databindings

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import com.sigmapool.app.R

fun showEditTextAlertDialog(context: Context, submitAction: (Float) -> Unit) {
    val dialogBuilder = AlertDialog.Builder(context).create()
    val inflater = LayoutInflater.from(context)
    val dialogView = inflater.inflate(R.layout.custom_dialog, null)

    val editText = dialogView.findViewById(R.id.editText) as EditText
    val submitBtn = dialogView.findViewById(R.id.buttonSubmit) as Button
    val cancelBtn = dialogView.findViewById(R.id.buttonCancel) as Button

    cancelBtn.setOnClickListener { dialogBuilder.dismiss() }
    submitBtn.setOnClickListener {
        val text = editText.text.toString()
        if (text.isEmpty()) return@setOnClickListener

        submitAction.invoke(text.toFloat())
        dialogBuilder.dismiss()
    }

    dialogBuilder.setView(dialogView)
    dialogBuilder.show()
}

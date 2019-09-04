package com.sigmapool.app.utils.databindings

import androidx.databinding.BindingConversion
import com.sigmapool.app.screens.settings.viewModel.LabelItem
import com.sigmapool.app.screens.settings.viewModel.SchemeItem

@BindingConversion
fun convertHobbiesToString(labelItem: LabelItem): String = labelItem.label

@BindingConversion
fun convertHobbiesToString(schemeItem: SchemeItem): String = schemeItem.label
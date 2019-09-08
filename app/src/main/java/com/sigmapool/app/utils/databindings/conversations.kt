package com.sigmapool.app.utils.databindings

import androidx.databinding.BindingConversion
import com.sigmapool.app.screens.settings.viewModel.SchemeItem
import com.sigmapool.app.utils.vm.LabelItem

@BindingConversion
fun convertHobbiesToString(labelItem: LabelItem): String = labelItem.label

@BindingConversion
fun convertHobbiesToString(schemeItem: SchemeItem): String = schemeItem.label
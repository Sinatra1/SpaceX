package com.example.core_data.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class FormattedEditText(
    context: Context,
    attrs: AttributeSet
) : AppCompatEditText(context, attrs) {

    init {
        inputType
    }

}
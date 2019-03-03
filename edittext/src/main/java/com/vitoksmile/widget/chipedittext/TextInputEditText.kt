package com.vitoksmile.widget.chipedittext

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText

class TextInputEditText(context: Context, attrs: AttributeSet? = null) :
        TextInputEditText(context, attrs) {

    /**
     * Interface to communicate with controller
     */
    var interaction: ChipEditTextLayout.Interaction? = null

    init {
        gravity = Gravity.CENTER_VERTICAL

        val paddingTop = resources.getDimensionPixelSize(R.dimen.editTextPaddingTop)
        val paddingBottom = resources.getDimensionPixelSize(R.dimen.editTextPaddingBottom)
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

        setSingleLine(true)
        maxLines = 1

        setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE)
        onDoneClicked {
            interaction?.onDoneClicked(text.toString())?.not() ?: true
        }
    }
}
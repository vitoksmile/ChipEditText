package com.vitoksmile.widget.chipedittext

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.annotation.StyleableRes

inline fun EditText.onDoneClicked(crossinline action: () -> Boolean) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action()
        } else false
    }
}

inline fun View.getAttributes(
        set: AttributeSet?,
        @StyleableRes attrs: IntArray,
        crossinline action: TypedArray.() -> Unit
) {
    set ?: return
    val attributes = context.theme.obtainStyledAttributes(set, attrs, 0, 0)
    attributes.action()
    attributes.recycle()
}
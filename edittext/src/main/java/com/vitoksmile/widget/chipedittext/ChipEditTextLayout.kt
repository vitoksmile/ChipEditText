package com.vitoksmile.widget.chipedittext

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ScrollView
import com.google.android.material.chip.ChipGroup

class ChipEditTextLayout(context: Context, attrs: AttributeSet? = null) :
        ScrollView(context, attrs) {

    interface Interaction {

        /**
         * @param text New entered text
         *
         * @return true if need to hide keyboard
         */
        fun onDoneClicked(text: String): Boolean

        /**
         * @return true if need to remove chip view
         */
        fun onRemoveClicked(chip: Chip): Boolean
    }

    private val chips = linkedSetOf<Chip>()

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    private val chipGroup: ChipGroup?
        get() = getChildAt(0) as? ChipGroup

    private val editText: TextInputEditText by lazy {
        TextInputEditText(context)
    }

    // Transition to animate chips
    private val chipsTransition = LayoutTransition()

    /**
     * Interface to communicate with controller
     */
    var interaction: Interaction? = null
        set(value) {
            field = value
            editText.interaction = field
        }

    /**
     * Hint for [editText]
     */
    var hint: String? = null
        set(value) {
            field = value
            editText.hint = value
        }

    init {
        // Create and add chip group
        ChipGroup(context).apply {
            layoutTransition = chipsTransition
        }.also { addView(it) }

        getAttributes(attrs, R.styleable.ChipEditTextLayout) {
            hint = getString(R.styleable.ChipEditTextLayout_hint)
        }

        update()
    }

    fun setChips(chips: List<Chip>) {
        // Clear previous text
        editText.text = null

        // Set chips
        this.chips.apply {
            clear()
            addAll(chips)
        }

        // Update layout
        update()
    }

    private fun update() {
        val group = chipGroup ?: return

        // Prepare layout transition to add chips
        chipsTransition.beforeAdding(group)

        // Add edit text
        if (group.childCount == 0) {
            group.addView(editText)
        }

        // Remove all chips
        if (group.childCount > 1) {
            group.removeViews(0, group.childCount - 1)
        }

        // Add new chips
        chips.forEach { chip ->
            val view = createChip().apply {
                text = chip.text
                tag = chip

                setOnCloseIconClickListener {
                    if (interaction?.onRemoveClicked(chip) == true) {
                        group.removeView(this)
                    }
                }
            }

            // Add chip before edit text
            group.addView(view, group.childCount - 1)
        }

        chipsTransition.afterAdding()
    }

    private fun createChip(): com.google.android.material.chip.Chip =
            inflater.inflate(R.layout.chip, this, false) as com.google.android.material.chip.Chip
}
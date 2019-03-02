package com.vitoksmile.widget.chipedittext

import android.animation.LayoutTransition
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.ChipGroup

/**
 * Transition to disable animate already added chips
 */
class LayoutTransition : LayoutTransition() {

    /**
     * Hold already added chips
     */
    private val chips = mutableListOf<Chip>()

    fun beforeAdding(chipGroup: ChipGroup) {
        chips.clear()

        for (i in 0 until chipGroup.childCount) {
            val child = chipGroup.getChildAt(i)
            val chip = (child as? com.google.android.material.chip.Chip)?.tag as? Chip ?: continue
            chips.add(chip)
        }
    }

    fun afterAdding() {
        chips.clear()
    }

    override fun addChild(parent: ViewGroup?, child: View?) {
        val chip = (child as? com.google.android.material.chip.Chip)?.tag as? Chip

        // Animate only if chip is not added
        if (chip == null || !chips.contains(chip)) {
            super.addChild(parent, child)
            return
        }
    }
}
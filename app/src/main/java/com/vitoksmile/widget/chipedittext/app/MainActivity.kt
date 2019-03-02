package com.vitoksmile.widget.chipedittext.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vitoksmile.widget.chipedittext.Chip
import com.vitoksmile.widget.chipedittext.ChipEditTextLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChipEditTextLayout.Interaction {

    private val users = linkedSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipEditText.interaction = this
    }

    override fun onDoneClicked(text: String): Boolean {
        if (text.isEmpty()) {
            Toast.makeText(this, "Username should be not empty", Toast.LENGTH_SHORT).show()
            return false
        }

        // Add new user
        users.add(text)

        // Notify edit text
        chipEditText.setChips(users.map { Chip(it) })
        return true
    }

    override fun onRemoveClicked(chip: Chip): Boolean {
        users.remove(chip.text)
        return true
    }
}

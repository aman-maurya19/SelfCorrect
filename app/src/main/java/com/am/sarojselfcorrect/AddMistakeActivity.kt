package com.am.sarojselfcorrect

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class AddMistakeActivity : AppCompatActivity() {

    private lateinit var viewModel: AddMistakeViewModel

    private lateinit var etTitle: TextInputEditText
    private lateinit var etDesc: TextInputEditText
    private lateinit var etLesson: TextInputEditText
    private lateinit var spinner: Spinner
    private lateinit var btnSave: Button

    private lateinit var btnCancel: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mistake)

        viewModel = ViewModelProvider(this)[AddMistakeViewModel::class.java]

        etTitle = findViewById(R.id.etTitle)
        etDesc = findViewById(R.id.etDesc)
        etLesson = findViewById(R.id.etLesson)
        spinner = findViewById(R.id.spinner)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.Cancel)

        btnCancel.setOnClickListener {
            finish()
        }


        // Spinner
        val categories = listOf(
            "Study",
            "Work",
            "Health",
            "Money",
            "Behaviour"
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = spinnerAdapter

        btnSave.setOnClickListener {

            val title = etTitle.text?.toString()?.trim() ?: ""
            val desc = etDesc.text?.toString()?.trim() ?: ""
            val lesson = etLesson.text?.toString()?.trim() ?: ""

            if (title.isEmpty()) {
                etTitle.error = "Title required"
                return@setOnClickListener
            }

            if (desc.isEmpty()) {
                etDesc.error = "Description required"
                return@setOnClickListener
            }

            // lesson optional
            val mistake = MistakeEntity(
                title = title,
                description = desc,
                category = spinner.selectedItem.toString(),
                lesson = lesson
            )

            viewModel.addMistake(mistake)
            finish()
        }
    }
}

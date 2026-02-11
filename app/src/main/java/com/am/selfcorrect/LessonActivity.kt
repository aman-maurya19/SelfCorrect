package com.am.selfcorrect

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonActivity : AppCompatActivity() {

    private lateinit var viewModel: LessonViewModel
    private lateinit var adapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val rvLessons = findViewById<RecyclerView>(R.id.rvLessons)
        val emptyState = findViewById<View>(R.id.emptyState)
        val tvTotalMistakes = findViewById<TextView>(R.id.tvTotalMistakes)
        val tvLessonsCount = findViewById<TextView>(R.id.tvLessonsCount)

        rvLessons.layoutManager = LinearLayoutManager(this)
        adapter = LessonAdapter()
        rvLessons.adapter = adapter

        viewModel = ViewModelProvider(this)[LessonViewModel::class.java]

        viewModel.lessons.observe(this) { list ->

            tvLessonsCount.text = list.size.toString()

            if (list.isEmpty()) {
                emptyState.visibility = View.VISIBLE
                rvLessons.visibility = View.GONE
            } else {
                emptyState.visibility = View.GONE
                rvLessons.visibility = View.VISIBLE
                adapter.submitList(list)
            }
        }

        viewModel.totalMistakes.observe(this) { total ->
            tvTotalMistakes.text = total.toString()
        }
    }
}

package com.am.sarojselfcorrect

import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MistakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivity(Intent(this, AddMistakeActivity::class.java))
        }

        findViewById<Button>(R.id.alllesson).setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        // RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvMistakes)
        val emptyState = findViewById<LinearLayout>(R.id.emptyState)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = MistakeAdapter()
        rv.adapter = adapter

        // Stats
        val tvToday = findViewById<TextView>(R.id.tvToday)
        val tvWeek = findViewById<TextView>(R.id.tvWeek)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.mistakes.observe(this) { list ->

            // 🔥 LIST
            adapter.submitList(list)

            // 🔥 TOTAL
            tvTotal.text = list.size.toString()

            // 🔥 TODAY (accurate)
            val todayCount = list.count {
                DateUtils.isToday(it.date)
            }
            tvToday.text = todayCount.toString()

            // 🔥 WEEK (proper calendar based)
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -7)
            val weekStart = calendar.timeInMillis

            val weekCount = list.count {
                it.date >= weekStart
            }
            tvWeek.text = weekCount.toString()

            // 🔥 EMPTY STATE
            if (list.isEmpty()) {
                emptyState.visibility = View.VISIBLE
                rv.visibility = View.GONE
            } else {
                emptyState.visibility = View.GONE
                rv.visibility = View.VISIBLE
            }
        }
    }
}
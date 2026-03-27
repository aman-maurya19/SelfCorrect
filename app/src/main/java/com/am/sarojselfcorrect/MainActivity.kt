package com.am.sarojselfcorrect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MistakeAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivity(Intent(this, AddMistakeActivity::class.java))
        }

        findViewById<Button>(R.id.alllesson).setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        val rv = findViewById<RecyclerView>(R.id.rvMistakes)
        val emptyState = findViewById<LinearLayout>(R.id.emptyState)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = MistakeAdapter()
        rv.adapter = adapter

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.mistakes.observe(this) { list ->
            adapter.submitList(list)

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

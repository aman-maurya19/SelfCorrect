package com.am.sarojselfcorrect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class First_screen : AppCompatActivity() {

    private lateinit var btn : Button
    private lateinit var policttext: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_screen)

        btn = findViewById(R.id.btnStart)
        policttext = findViewById(R.id.policy)

        btn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        policttext.setOnClickListener {
            val url = "https://sites.google.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
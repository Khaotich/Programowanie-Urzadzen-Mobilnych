package com.example.list1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Summary : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val points = findViewById<TextView>(R.id.POINTS) as TextView
        val correctAnswers = findViewById<TextView>(R.id.CORRECTANSWERS) as TextView
        val cheats = findViewById<TextView>(R.id.CHEATS) as TextView
        val bundle = intent.extras

        points.text = bundle?.getString("points")
        correctAnswers.text = bundle?.getString("correct_answers")
        cheats.text = bundle?.getString("cheats")
    }
}
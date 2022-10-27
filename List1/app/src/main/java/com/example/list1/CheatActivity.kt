package com.example.list1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CheatActivity : AppCompatActivity()
{
    val urls = listOf<String>(
        "https://pl.wikipedia.org/wiki/Przyspieszenie_ziemskie",
        "https://zapytajfizyka.fuw.edu.pl/pytania/w-ile-czasu-swiatlo" +
                "-przebywa-droge-ze-slonca-do-ziemi/",
        "https://pl.wikipedia.org/wiki/Sublimacja",
        "https://pl.wikipedia.org/wiki/Pr%C4%99dko%C5%9B%C4%87_d%C5%BAwi%C4%99ku",
        "https://pl.wikipedia.org/wiki/Prawo_powszechnego_ci%C4%85%C5%BCenia",
        "https://pl.wikipedia.org/wiki/Kelwin",
        "https://pl.wikipedia.org/wiki/Krzem",
        "https://pl.wikipedia.org/wiki/%C5%9Awiat%C5%82o",
        "https://pl.wikipedia.org/wiki/Zderzenie_spr%C4%99%C5%BCyste",
        "https://pl.wikipedia.org/wiki/Rezystancja"
    )

    var index = 0
    var answer = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val widget = findViewById<TextView>(R.id.text_answer) as TextView
        val bundle = intent.extras
        val answer = bundle?.getBoolean("booleanAnswer", true)

        if(answer == true) widget.text = "Yes"
        else widget.text = "No"
    }

    fun url(view: View)
    {
        val bundle = intent.extras
        val index = bundle?.getInt("index", 0)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urls[index!!]))
        startActivity(intent)
    }
}
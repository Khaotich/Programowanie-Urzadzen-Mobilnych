package com.example.list1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    val QUESTIONS = listOf<String>(
        "Czy przyśpieszenie zmiemiskie wynosi 9,81 m/s^2?", //true
        "Czy odległość zmiemi od słońca wynosi ~ 150 000 000 km?", //true
        "Czy sublimacja jest przemianą fazową przejścia ze stanu stałego w stan ciekły?", //false
        "Czy prędkość dzwięku wynosi ~ 1225 km/h?", //true
        "Czy według prawa powrzechnego ciążenia, ciała o mniejszej masie przyciągają się mocniej niż" +
                "ciała o większych masach masach?", //false
        "Czy 0°C jest równe 273,15 stopni Kelwina?", //true
        "Czy krzem jest nadprzewodnikiem prądowym?", //false
        "Czy światłlo jest falą mechaniczną?", //false
        "Zakładając że ciała o identycznych masach zderzą się centralnie, czy uderzone ciało przejmie" +
                " całą enegrię przy uderzeniu?", //true
        "Czy wzór na opór ma postać R = I / U?" //false
    )

    val ANSWERS = listOf<Boolean>(true, true, false, true, false, true, false, false, true, false)
    val INDEXS = mutableListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled()

    var points = 0
    var cheats = 0
    var correctAnswers = 0

    var index = 0
    var cheat = true
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val question = findViewById<TextView>(R.id.question) as TextView
        question.text = QUESTIONS[INDEXS[0]]
    }

    fun answer(view: View)
    {
        count++
        if(count < 10)
        {
            val question = findViewById<TextView>(R.id.question) as TextView
            val ans = view.id == R.id.button_yes
            cheat = true

            if(ans == ANSWERS[INDEXS[index]])
            {
                points += 10
                correctAnswers++
            }

            index++
            question.text = QUESTIONS[INDEXS[index]]
        }
        else
        {
            val myIntent = Intent(this, Summary::class.java)

            val bundle = Bundle()
            bundle.putString("points", points.toString());
            bundle.putString("correct_answers", correctAnswers.toString())
            bundle.putString("cheats", cheats.toString())

            myIntent.putExtras(bundle)
            startActivity(myIntent)
        }
    }

    fun cheat(view: View)
    {
        if(cheat)
        {
            cheat = false
            cheats++
            points -= 15
        }

        val myIntent = Intent(this, CheatActivity::class.java)

        val bundle = Bundle()
        bundle.putBoolean("booleanAnswer", ANSWERS[INDEXS[index]]);
        bundle.putInt("index", INDEXS[index])

        myIntent.putExtras(bundle)
        startActivity(myIntent)
    }
}
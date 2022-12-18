package com.example.list3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class CreateFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create, container, false)
        val file = this.context?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val ids = file?.getStringSet("ids", setOf())?.toMutableList()

        view.findViewById<Button>(R.id.add_button).setOnClickListener{
            val name =  view.findViewById<EditText>(R.id.name_new).text
            val content = view.findViewById<EditText>(R.id.content_new).text

            val edit = file?.edit()
            edit?.apply{
                val id = java.util.UUID.randomUUID().toString()
                ids?.add(id)
                putStringSet("ids", ids?.toMutableSet())
                putString(id_name(id), name.toString())
                putString(id_content(id), content.toString())
                apply()
            }

            Navigation.findNavController(view).navigate(R.id.list)
        }

        return view
    }
}
package com.example.lista2

import android.app.FragmentBreadCrumbs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        view.findViewById<TextView>(R.id.Name).text = arguments?.getString("name")
        view.findViewById<TextView>(R.id.Id).text = arguments?.getString("id")
        view.findViewById<TextView>(R.id.Date).text = arguments?.getString("date")
        view.findViewById<TextView>(R.id.Des).text = arguments?.getString("description")

        val sol =  arguments?.getBoolean("status")
        if(sol == true)
            view.findViewById<TextView>(R.id.Solved).text = "Solved"
        else
            view.findViewById<TextView>(R.id.Solved).text = "Not Solved"

        view.findViewById<Button>(R.id.back).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.listFragment)
        }

        return view
    }
}
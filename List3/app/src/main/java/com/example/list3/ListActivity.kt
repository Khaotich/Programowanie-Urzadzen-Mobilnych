package com.example.list3

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.navigation.Navigation

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val listView =  view.findViewById<ListView>(R.id.list_view)
        val file = context?.getSharedPreferences("data", MODE_PRIVATE)
        val list = ArrayList<HashMap<String, Any>>()
        val ids = file?.getStringSet("ids", setOf())

        view.findViewById<Button>(R.id.button_add_task).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.create)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val args = Bundle()
            args.putString("id", ids?.elementAt(position)!!)
            Navigation.findNavController(view).navigate(R.id.detail, args)
        }

        if(ids != null)
        {
            for(i in ids)
            {
                val map = HashMap<String,Any>()
                map["names"] = file.getString(id_name(i), i.toString()).toString()
                map["contents"] = file.getString(id_content(i), i.toString()).toString()
                list.add(map)
            }
        }

        val from = arrayOf("names", "contents")
        val to = intArrayOf(R.id.name, R.id.des)
        val adapter = SimpleAdapter(context, list, R.layout.item, from, to)

        listView.adapter = adapter
        return view
    }
}
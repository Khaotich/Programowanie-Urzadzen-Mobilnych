package com.example.list3

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.edit
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val name_text = view.findViewById<EditText>(R.id.name_update)
        val content_text =  view.findViewById<EditText>(R.id.content_update)
        val back_button = view.findViewById<Button>(R.id.back_button)
        val update_button = view.findViewById<Button>(R.id.edit_button)
        val del_button = view.findViewById<Button>(R.id.del_button)

        val file = context?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val edit = file?.edit()
        val ids = file?.getStringSet("ids", setOf())?.toMutableList()
        val id = arguments?.getString("id", "")
        val name = file?.getString(id_name(id!!), "")
        val content = file?.getString(id_content(id!!), "")

        name_text.setText(name)
        content_text.setText(content)

        back_button.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.list)
        }

        del_button.setOnClickListener{
            val dialogClickListener = DialogInterface.OnClickListener{ dialog, which ->
                when(which)
                {
                    DialogInterface.BUTTON_POSITIVE ->
                    {
                        ids?.remove(id)
                        edit?.putStringSet("ids", ids?.toSet())
                        edit?.remove(id_name(id!!))
                        edit?.remove(id_content(id!!))
                        edit?.apply()

                        Navigation.findNavController(view).navigate(R.id.list)
                    }
                    DialogInterface.BUTTON_NEGATIVE ->
                    {
                        dialog.dismiss()
                    }
                }
            }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Are you sure to delete this task?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show()
        }

        update_button.setOnClickListener{
            val dialogClickListener = DialogInterface.OnClickListener{ dialog, which ->
                when(which)
                {
                    DialogInterface.BUTTON_POSITIVE ->
                    {
                        val new_content = content_text.text
                        val new_name = name_text.text

                        edit?.putString(id_name(id!!), new_name.toString())
                        edit?.putString(id_content(id!!), new_content.toString())
                        edit?.apply()
                        Navigation.findNavController(view).navigate(R.id.list)
                    }
                    DialogInterface.BUTTON_NEGATIVE ->
                    {
                        dialog.dismiss()
                    }
                }
            }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Are you sure to edit this task?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show()
        }

        return view
    }
}
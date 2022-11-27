package com.example.lista2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class ListFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val image = R.mipmap.handcuffs_foreground
        val listView =  view.findViewById<ListView>(R.id.list_view)

        val names = arrayOf(
            "Crime #1", "Crime #2", "Crime #3", "Crime #4", "Crime #5",
            "Crime #6", "Crime #7", "Crime #8", "Crime #9", "Crime #10",
            "Crime #11", "Crime #12", "Crime #13", "Crime #14", "Crime #15",
            "Crime #16", "Crime #17", "Crime #18", "Crime #19", "Crime #20"
        )

        val dates = arrayOf(
            "10.05.2022 10:38", "01.06.2022 12:31", "09.06.2022 08:00",
            "15.06.2022 12:38", "20.06.2022 14:59", "25.06.2022 11:48",
            "30.06.2022 09:02", "02.07.2022 15:00", "10.07.2022 14:38",
            "01.10.2022 08:00", "07.10.2022 11:28", "15.10.2022 10:38",
            "20.10.2022 07:49", "25.10.2022 10:38", "30.10.2022 12:12",
            "05.11.2022 11:33", "10.11.2022 10:38", "15.11.2022 08:32",
            "20.11.2022 12:18", "20.11.2022 13:48"
        )

        val des = arrayOf(
            "Stealing a Bicycle", "Shoplifting", "Tagging and Graffiti",
            "Drawing on Public Restroom Walls", "Keying a Car and Cutting Auto Tires",
            "Underage Consumption of Alcohol", "Possessing an Open Container in Car",
            "Fighting in a Public Place", "Cursing at a Teacher",
            "Shoving or Pushina Person",  "Smoking Marijuana in a Public Place",
            "Chewing or Smoking Tobacco at School", "Sneaking Out of Home After Curfew",
            "Cutting Class", "Having Excessive Tardies", "Entering a Vacant Building",
            "Picking Flowers in a Restricted or Private Area", "Bullying",
            "Committing Fraud Via E-Mail", "Possessing a BB or Pellet Gun While Underage"
        )

        val ids = arrayOf(
            "001", "002", "003", "004", "005", "006", "007", "008", "009", "010",
            "011", "012", "013", "014", "015", "016", "017", "018", "019", "020"
        )

        val solved = arrayOf(
            false, false, true, true, false, true, true, true, false, false,
            false, false, true, true, false, true, true, true, false, false
        )

        listView.setOnItemClickListener { _, _, position, _ ->
            val args = Bundle()

            args.putString("name", names[position])
            args.putString("date", dates[position])
            args.putString("id", ids[position])
            args.putString("description", des[position])
            args.putBoolean("status", solved[position])

            Navigation.findNavController(view).navigate(R.id.detailFragment, args)
        }

        val list = ArrayList<HashMap<String,Any>>()
        for(i in names.indices)
        {
            val map = HashMap<String,Any>()

            map["names"] = names[i]
            map["dates"] = dates[i]
            if(!solved[i]) map["img"] = image

            list.add(map)
        }

        val from = arrayOf("names", "dates", "img")
        val to = intArrayOf(R.id.name, R.id.des, R.id.image)
        val adapter = SimpleAdapter(this.context, list, R.layout.item, from, to)

        listView.adapter = adapter
        return view
    }
}
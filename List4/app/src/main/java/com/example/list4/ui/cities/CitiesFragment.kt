package com.example.list4.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.list4.databinding.FragmentCitiesBinding
import com.example.list4.PlaceholderApi
import com.example.list4.R
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.await

class CitiesFragment : Fragment()
{
    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        GlobalScope.launch(Dispatchers.Main)
        {
            val listView =  root.findViewById<ListView>(R.id.cities_view)
            val list = ArrayList<HashMap<String, Any>>()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restcountries.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(PlaceholderApi::class.java)

            val posts = withContext(Dispatchers.IO)
            {
                val response = async {api.posts()}
                return@withContext response.await()
            }

            val call = posts.await()
            call.forEach{
                val map = HashMap<String,Any>()
                map["countries"] = it.name
                map["cities"] = it.capital
                list.add(map)
                println(it.name + it.capital)
            }

            val from = arrayOf("countries", "cities")
            val to = intArrayOf(R.id.name, R.id.des)
            val adapter = SimpleAdapter(context, list, R.layout.item, from, to)

            listView.adapter = adapter
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
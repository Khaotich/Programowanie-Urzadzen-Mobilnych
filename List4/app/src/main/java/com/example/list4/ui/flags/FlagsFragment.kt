package com.example.list4.ui.flags

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.list4.PlaceholderApi
import com.example.list4.R
import com.example.list4.databinding.FragmentFlagsBinding
import kotlinx.coroutines.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

object RetrofitImage {

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://google.com").build()
    }

    private interface API {
        @GET
        fun getImageData(@Url url: String): Call<ResponseBody>
    }

    private val api : API by lazy  { provideRetrofit().create(API::class.java) }

    fun getBitmapFrom(url: String, onComplete: (Bitmap?) -> Unit) {

        api.getImageData(url).enqueue(object : retrofit2.Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                onComplete(null)
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response == null || !response.isSuccessful || response.body() == null || response.errorBody() != null) {
                    onComplete(null)
                    return
                }
                val bytes = response.body()!!.bytes()
                onComplete(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
            }
        })
    }
}

class FlagsFragment : Fragment() {

    private var _binding: FragmentFlagsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlagsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        GlobalScope.launch(Dispatchers.Main)
        {
            val listView =  root.findViewById<ListView>(R.id.flags_view)
            val list = ArrayList<HashMap<String, Any>>()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restcountries.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(PlaceholderApi::class.java)

            val posts_imgs = withContext(Dispatchers.IO)
            {
                val response = async {api.posts_img()}
                return@withContext response.await()
            }

            val call = posts_imgs.await()
            call.forEach{
                val map = HashMap<String,Any>()
                map["countries"] = it.name
                var tmp: String = String()
                for(i in 20..it.flag.length - 5) tmp += it.flag[i]
                val h: String = "https://flagcdn.com/w320/"
                val adr: String = "$h$tmp.png"

                RetrofitImage.getBitmapFrom(adr)
                {
                    if (it != null) map["flags"] = it
                }

                list.add(map)
            }

            val from = arrayOf("countries", "flags")
            val to = intArrayOf(R.id.name, R.id.image)
            val adapter = ExtendedSimpleAdapter(context, list, R.layout.item_img, from, to)

            listView.adapter = adapter
        }
        return root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}
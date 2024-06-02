package com.rlibanez.sectionlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rlibanez.sectionlist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        TODO("Not yet implemented")
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            // La URL siempre debe acabar en /
            .baseUrl("http://192.168.2.2:8008/api/sections/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSection("$query")
            val section = call.body()
            if(call.isSuccessful) {
                // Show RecyclerView
            } else {
                // Error
            }
        }
    }

}
package com.rlibanez.sectionlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.rlibanez.sectionlist.data.Section
import com.rlibanez.sectionlist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SectionAdapter
    private var sectionList = mutableListOf<Section>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svSections.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = SectionAdapter(sectionList)
        binding.rvSections.layoutManager = LinearLayoutManager(this)
        binding.rvSections.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            // La URL siempre debe acabar en /
            .baseUrl("http://192.168.2.2:8008/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var allSections = emptyList<Section>()
            try {
                allSections = getRetrofit().create(APIService::class.java).getAllSections("$query")
            } catch (e: Exception) {
            }

            runOnUiThread {
                if(allSections.isNotEmpty()) {
                    //val sections: List<Section> = sections?.sections ?: emptyList()
                    sectionList.clear()
                    sectionList.addAll(allSections)
                    adapter.notifyDataSetChanged()
                } else {
                    // Error
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    // Implementación obligatoria de OnQueryTextListener
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}
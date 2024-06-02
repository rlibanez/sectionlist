package com.rlibanez.sectionlist

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
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
            try {
                val response = getRetrofit().create(APIService::class.java).getAllSections(query)
                runOnUiThread {
                    if (response.isSuccessful) {
                        val sections = response.body() ?: emptyList()
                        sectionList.clear()
                        sectionList.addAll(sections)
                        adapter.notifyDataSetChanged()
                    } else {
                        showError("Ha habido un error")
                    }
                }
                hideKeyBoard()
            } catch (e: Exception) {
                runOnUiThread {
                    showError("Error de conexión")
                }
            }
        }
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)

    }

    private fun showError(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
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
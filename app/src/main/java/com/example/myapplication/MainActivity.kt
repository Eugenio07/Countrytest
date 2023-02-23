package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import dev.mvillasenor.evaluation.DataSource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createList()
    }

    private fun createList() {
        val dataSource = DataSource(this)
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = countriesAdapter(dataSource.getCountries(), dataSource.getContinents())
    }
}
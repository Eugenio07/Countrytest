package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBinding
import dev.mvillasenor.evaluation.Country

class countriesAdapter(private val countries: List<Country>, private val continent: Map<String, String>): RecyclerView.Adapter<countriesAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun render(country :String, continent:String){
            with(binding){
                tvPais.text = country
                tvContinente.text = continent
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(countries[position].name, continent[countries[position].continent] ?:"")
    }
}
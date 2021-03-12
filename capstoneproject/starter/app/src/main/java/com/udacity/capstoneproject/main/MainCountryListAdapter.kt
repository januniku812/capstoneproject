package com.udacity.capstoneproject.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.capstoneproject.*
import com.udacity.capstoneproject.databinding.CountryMainItemBinding

class MainCountryListAdapter(private val asteroidClick: (asteroid: Country) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Country, MainCountryListAdapter.ItemViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val mainItemBinding: CountryMainItemBinding = CountryMainItemBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(mainItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.also {
            it.itemView.setOnClickListener{
                asteroidClick(asteroid)
            }
            it.bind(asteroid)
        }

    }


    class ItemViewHolder(private var binding: CountryMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryToBind: Country) {
//            setting text and content description for all the views
              binding.country = countryToBind
              binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CountryMainItemBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding)
            }
        }



    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

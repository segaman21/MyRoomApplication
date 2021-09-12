package com.example.myroomapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.databinding.AnimalItemBinding


class AnimalsListAdapter :
    ListAdapter<Animals, AnimalsListAdapter.AnimalsViewHolder>(WORDS_COMPARATOR) {

    class AnimalsViewHolder(private val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animals: Animals) {
            binding.nameDesc.text = animals.name
            binding.ageDesc.text = animals.age
            binding.breedDesc.text = animals.breed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val itemBinding = AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalsViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Animals>() {
            override fun areItemsTheSame(oldItem: Animals, newItem: Animals): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Animals, newItem: Animals): Boolean {
                return oldItem == newItem
            }
        }
    }
}
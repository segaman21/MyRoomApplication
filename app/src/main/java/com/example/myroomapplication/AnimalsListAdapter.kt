package com.example.myroomapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomapplication.addNewAnimal.NewAnimalActivity
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.databinding.AnimalItemBinding

class AnimalsListAdapter(private val listener: AnimalsListener) :
    ListAdapter<Animals, AnimalsListAdapter.AnimalsViewHolder>(WORDS_COMPARATOR) {

    class AnimalsViewHolder(
        private val binding: AnimalItemBinding, private val listener: AnimalsListener
    ) : RecyclerView.ViewHolder(binding.root) {


        private fun initOnCLock(animals: Animals) {
            binding.deleteChosen.setOnClickListener {
                listener.delete(animals.id)
            }
            binding.updateChosen.setOnClickListener {
                listener.update(animals)
            }
        }

        fun bind(animals: Animals) {
            initOnCLock(animals)
            binding.nameDesc.text = animals.name
            binding.ageDesc.text = animals.age
            binding.breedDesc.text = animals.breed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val itemBinding =
            AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalsViewHolder(itemBinding, listener)
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
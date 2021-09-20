package com.example.myroomapplication.ui.animalsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomapplication.AnimalsListener
import com.example.myroomapplication.data.Animal
import com.example.myroomapplication.databinding.AnimalItemBinding

class AnimalsListAdapter(private val listener: AnimalsListener) :
    ListAdapter<Animal, AnimalsListAdapter.AnimalsViewHolder>(WORDS_COMPARATOR) {

    class AnimalsViewHolder(
        private val binding: AnimalItemBinding, private val listener: AnimalsListener
    ) : RecyclerView.ViewHolder(binding.root) {


        private fun initOnCLock(animal: Animal) {
            binding.deleteChosen.setOnClickListener {
                listener.delete(animal.id)
            }
            binding.updateChosen.setOnClickListener {
                listener.update(animal)
            }
        }

        fun bind(animal: Animal) {
            initOnCLock(animal)
            binding.nameDesc.text = animal.name
            binding.ageDesc.text = animal.age
            binding.breedDesc.text = animal.breed
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
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem == newItem
            }
        }
    }
}
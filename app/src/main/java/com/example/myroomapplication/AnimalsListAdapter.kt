package com.example.myroomapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomapplication.database.Animals


class AnimalsListAdapter(private val animalsList: List<Animals>) :
    RecyclerView.Adapter<AnimalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        return AnimalsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(animalsList[position])
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }
}

class AnimalsViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    private val animalsName: TextView = itemView.findViewById(R.id.name_desc)
    private val animalsAge: TextView = itemView.findViewById(R.id.age_desc)
    private val animalsBreed: TextView = itemView.findViewById(R.id.breed_desc)

    fun bind(animals: Animals) {
        animalsName.text = animals.name
        animalsAge.text = animals.age
        animalsBreed.text = animals.breed
    }

    companion object {
        fun create(parent: ViewGroup): AnimalsViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.animal_item, parent, false)
            return AnimalsViewHolder(view)
        }
    }
}
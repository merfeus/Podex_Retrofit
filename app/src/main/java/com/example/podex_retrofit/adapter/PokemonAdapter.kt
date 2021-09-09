package com.example.podex_retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.podex_retrofit.R
import com.example.podex_retrofit.databinding.PokeItemBinding
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.utils.toUpperFirstChar

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private var listOfPokemon: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.poke_item, parent, false)
        return PokemonViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        listOfPokemon[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfPokemon.size

    fun refesh(newList: List<Pokemon>) {
        listOfPokemon = arrayListOf()
        listOfPokemon.addAll(newList)
        notifyDataSetChanged()
    }
}

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("ResourceType")
    private var binding: PokeItemBinding = PokeItemBinding.bind(itemView)
    fun bind(pokemon: Pokemon) {

        binding.urlPokemon.text = "#${pokemon.extractIdFormUrl(withPads = true)}"
        binding.namePokemon.text = pokemon.name.toUpperFirstChar()
        pokemon.details?.let {
            Glide.with(itemView.context)
                .load(it.sprites.other.artWork?.image)
//                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.imagePokemon)

            val pokeTypeSetup = it.types[0].type.extractPokeSetup()
            binding.cardItem.setCardBackgroundColor(itemView.context.getColor(pokeTypeSetup.colorCard))
            binding.typesContainer.typeCardView1.setCardBackgroundColor(
                itemView.context.getColor(
                    pokeTypeSetup.colorType
                )
            )
            binding.typesContainer.typeImageView1.setImageDrawable(
                itemView.context.getDrawable(
                    pokeTypeSetup.icon
                )
            )
            binding.typesContainer.typeTextView1.text = it.types[0].type.typeName.toUpperFirstChar()

            if (it.types.size > 1) {
                val setupCard2 = it.types[1].type.extractPokeSetup()
                binding.typesContainer.typeCardView2.setCardBackgroundColor(
                    itemView.context.getColor(
                        setupCard2.colorType
                    )
                )
                binding.typesContainer.typeImageView2.setImageDrawable(
                    itemView.context.getDrawable(
                        setupCard2.icon
                    )
                )
                binding.typesContainer.typeTextView2.text =
                    it.types[1].type.typeName.toUpperFirstChar()
                binding.typesContainer.typeCardView2.visibility = View.VISIBLE
            } else {
                binding.typesContainer.typeCardView2.visibility = View.GONE
            }
        }

    }

}
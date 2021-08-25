package com.example.podex_retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podex_retrofit.R
import com.example.podex_retrofit.databinding.FiltersFragmentBinding
import com.example.podex_retrofit.databinding.TypesFiltersIconsBinding
import com.example.podex_retrofit.model.PokeTypeSetup
import com.example.podex_retrofit.model.Pokemon

class TypesAdapter: RecyclerView.Adapter<TypesViewHolder>() {

    private var listOfTypes: MutableList<PokeTypeSetup> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        val typesView = LayoutInflater.from(parent.context).inflate(R.layout.types_filters_icons, parent, false)
        return TypesViewHolder(typesView)
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        listOfTypes[position].apply {
            holder
        }
    }

    override fun getItemCount(): Int = listOfTypes.size

    fun refesh(newType: List<PokeTypeSetup>){
        listOfTypes = arrayListOf()
        listOfTypes.addAll(newType)
        notifyDataSetChanged()
    }
}

class TypesViewHolder(typesView: View): RecyclerView.ViewHolder(typesView){

     private var binding: TypesFiltersIconsBinding = TypesFiltersIconsBinding.bind(typesView)

//    fun bind(pokeType: PokeTypeSetup){
//
//
//        }
//
//    }
}

package com.example.podex_retrofit.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.podex_retrofit.R
import com.example.podex_retrofit.adapter.PokemonAdapter
import com.example.podex_retrofit.databinding.MainFragmentBinding
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = PokemonAdapter()

    private val observerPoke = Observer<List<Pokemon>>{
    adapter.refesh(it)
    }

    private val observerError = Observer<String?>{
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        binding.recyclerVIewPoke.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerVIewPoke.adapter= adapter

        viewModel.error.observe(viewLifecycleOwner,observerError)
        viewModel.poke.observe(viewLifecycleOwner, observerPoke)
        viewModel.fetchAllFromDataBase(requireContext())

    }

}
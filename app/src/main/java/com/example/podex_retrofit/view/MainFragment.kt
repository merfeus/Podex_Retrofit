package com.example.podex_retrofit.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.INVISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.podex_retrofit.R
import com.example.podex_retrofit.adapter.PokemonAdapter
import com.example.podex_retrofit.databinding.MainFragmentBinding
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.view.dailogs.FilterFragment
import com.example.podex_retrofit.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = PokemonAdapter()

    private val observerPoke = Observer<List<Pokemon>> {
        adapter.refesh(it)
    }

    private val observerError = Observer<String?> {
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
    }

    private val observerLoading = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.pokeAnimation.playAnimation()
        } else {
            binding.pokeAnimation.cancelAnimation()
            binding.pokeAnimation.visibility = INVISIBLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        binding.recyclerVIewPoke.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerVIewPoke.adapter = adapter

        viewModel.error.observe(viewLifecycleOwner, observerError)
        viewModel.poke.observe(viewLifecycleOwner, observerPoke)
        viewModel.isLoading.observe(viewLifecycleOwner, observerLoading)

        viewModel.fetchAllFromServer()

        binding.editTextSearch.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (it.length > 2) {
                        viewModel.fetchFilteredFromDatabase(it.toString())
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.isEmpty()) {
                        viewModel.fetchAllFromDatabase()
                    }
                }
            }
        })

        binding.imageFilters.setOnClickListener { showBottomSheetDialog() }

    }

    fun showBottomSheetDialog() {
        val bottomSheet = FilterFragment()
        bottomSheet.show(parentFragmentManager, "dialog_filters")
    }
}
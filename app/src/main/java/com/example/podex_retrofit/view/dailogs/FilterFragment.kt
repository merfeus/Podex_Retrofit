package com.example.podex_retrofit.view.dailogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podex_retrofit.R
import com.example.podex_retrofit.adapter.TypesAdapter
import com.example.podex_retrofit.databinding.FiltersFragmentBinding
import com.example.podex_retrofit.viewmodel.FilterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FiltersFragmentBinding
    private lateinit var viewModel: FilterViewModel
    private var adapter = TypesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filters_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FiltersFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)

        binding.recyclerViewTypes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewTypes.adapter = adapter
    }

    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

}
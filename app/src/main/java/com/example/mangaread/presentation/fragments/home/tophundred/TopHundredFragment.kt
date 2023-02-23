package com.example.mangaread.presentation.fragments.home.tophundred

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentTopHundredBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.MangaAdapter
import com.example.mangaread.presentation.base.BaseFragment
import com.example.mangaread.presentation.fragments.filters.FilterDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopHundredFragment : BaseFragment<MainViewModel,FragmentTopHundredBinding>(R.layout.fragment_top_hundred) {

    override val binding: FragmentTopHundredBinding by viewBinding(FragmentTopHundredBinding::bind)
    override val viewModel: MainViewModel by viewModel()
    private val topHundredAdapter = MangaAdapter(this::onItemClick)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun initialize() {
        super.initialize()
        binding.rvTopHundred.adapter = topHundredAdapter
        searchFilter()
    }

//    private fun filtration() {
//
//    }

//    private fun genreFilter() {
//        sharedViewModel.genre__title.observe(viewLifecycleOwner) {genre->
//            lifecycleScope.launch {
//                repeatOnLifecycle(Lifecycle.State.STARTED){
//                    viewModel.fetchMangaByGenre(genre).observe(viewLifecycleOwner) { list->
//                        topHundredAdapter.submitList(list)
//                    }
//                }
//            }
//        }
//    }
//
//    private fun typeFilter() {
//        sharedViewModel.type.observe(viewLifecycleOwner) {type->
//            lifecycleScope.launch {
//                repeatOnLifecycle(Lifecycle.State.STARTED){
//                    viewModel.fetchMangaByType(type).observe(viewLifecycleOwner) { list->
//                        topHundredAdapter.submitList(list)
//                    }
//                }
//            }
//        }
//    }
    private fun searchFilter() {
        binding.searchViewMain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
               lifecycleScope.launch {
                   repeatOnLifecycle(Lifecycle.State.STARTED){
                       viewModel.fetchTopMangaBySearch(p0.toString()).observe(viewLifecycleOwner) {
                           topHundredAdapter.submitList(it)
                       }
                   }
               }
            }
        })
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnFilter.setOnClickListener {
            val filterDialogFragment = FilterDialogFragment()
            val manager = requireActivity().supportFragmentManager
            filterDialogFragment.show(manager, "filter")
        }
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topHundredState.collect {
                    viewModel.fetchTopManga(100, 1).observe(viewLifecycleOwner) { topManga ->
                        topHundredAdapter.submitList(topManga.results)
                    }
                }
            }
        }
    }

    private fun onItemClick(position: Int) {
        sharedViewModel.saveId(position)
        findNavController().navigate(R.id.productDetailFragment)
    }


}
package com.example.mangaread.presentation.fragments.home.allmanga

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentAllMangaBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.MangaAdapter
import com.example.mangaread.presentation.base.BaseFragment
import com.example.mangaread.presentation.fragments.filters.FilterDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllMangaFragment : BaseFragment<MainViewModel,FragmentAllMangaBinding>(R.layout.fragment_all_manga) {

    override val binding: FragmentAllMangaBinding by viewBinding(FragmentAllMangaBinding::bind)
    override val viewModel: MainViewModel by viewModel()
    private val allMangaAdapter = MangaAdapter(this::onItemClick)
    private var page = 5
    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun initialize() {
        super.initialize()
        binding.rvAllmanga.adapter = allMangaAdapter
        searchFilter()
        filtration()
    }

    private fun filtration() {
        typeFilter()
        genreFilter()
    }

    private fun genreFilter() {
        sharedViewModel.genre__title.observe(viewLifecycleOwner) {genre->
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.fetchMangaByGenre(genre).observe(viewLifecycleOwner) { list->
                        allMangaAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun typeFilter() {
        sharedViewModel.type.observe(viewLifecycleOwner) {type->
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.fetchMangaByType(type).observe(viewLifecycleOwner) { list->
                        allMangaAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun searchFilter() {
        binding.searchViewMain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.fetchMangaBySearch(p0.toString().trim()).observe(viewLifecycleOwner) {
                            allMangaAdapter.submitList(it)
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

    private fun onItemClick(position: Int){
        sharedViewModel.saveId(position)
        findNavController().navigate(R.id.productDetailFragment)
    }


    override fun setupSubscribers() {
        super.setupSubscribers()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchAllManga(page, 0).observe(viewLifecycleOwner) {
                    allMangaAdapter.submitList(it.results)
//                    paging
                    if (sharedViewModel.type.equals("") || sharedViewModel.genre__title.equals("")){
                        paging()
                    }

                }
            }
        }
    }

    private fun paging() {
        binding.rvAllmanga.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchAllManga(++page * 5, 0)
                        .observe(viewLifecycleOwner) {
                            allMangaAdapter.submitList(it.results)
                        }
                    Log.e("page count", "onScrollStateChanged: $page")
                }
            }
        })
    }
}

package com.example.mangaread.presentation.fragments.filters

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaread.R
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.FilterAdapter
import com.example.mangaread.presentation.fragments.filters.genre.GenreDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilterDialogFragment : DialogFragment() {

    private val viewModel:MainViewModel by viewModel()
    private val filterAdapter = FilterAdapter(this::onItemClick)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_filter_dialog, container, false)

        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setGravity(Gravity.TOP or Gravity.RIGHT)

        val btn = root.findViewById<View>(R.id.btn_accept_filter)
        val toGenre = root.findViewById<View>(R.id.ic_toAllGenre)
        val drop = root.findViewById<View>(R.id.btn_drop_filter)

        drop.setOnClickListener {
            dialog?.dismiss()
        }

        toGenre.setOnClickListener {
            val genreDialogFragment = GenreDialogFragment()
            val manager = requireActivity().supportFragmentManager
            genreDialogFragment.show(manager, "genre")
        }

        btn.setOnClickListener {
            dialog?.dismiss()
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filterRv = dialog?.findViewById<RecyclerView>(R.id.filter_rv)
        filterRv?.adapter = filterAdapter
        fetchData()
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filterState.collect {
                    viewModel.fetchAllManga(10,1).observe(viewLifecycleOwner) {filter->
                        filterAdapter.submitList(filter.results)
                    }
                }
            }
        }
    }

    private fun onItemClick(type:String){
        sharedViewModel.saveType(type)
    }
}
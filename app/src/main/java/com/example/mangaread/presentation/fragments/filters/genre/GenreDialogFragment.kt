package com.example.mangaread.presentation.fragments.filters.genre

import android.annotation.SuppressLint
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
import com.example.mangaread.presentation.adapters.GenreAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreDialogFragment : DialogFragment() {


    private val viewModel:MainViewModel by viewModel()
    private val genreAdapter = GenreAdapter(this::onItemClick)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @SuppressLint("RtlHardcoded")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_genre_dialog, container, false)
        dialog?.window?.setGravity(Gravity.TOP or Gravity.RIGHT)


        val btn = root.findViewById<View>(R.id.btn_accept_filter)
        val drop = root.findViewById<View>(R.id.btn_drop_filter)

        btn.setOnClickListener {
            dialog?.dismiss()
        }
        drop.setOnClickListener {
            dialog?.dismiss()
        }

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genreRv = dialog?.findViewById<RecyclerView>(R.id.genre_rv)
        genreRv?.adapter = genreAdapter
        fetchData()
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.genreState.collect {
                    viewModel.fetchGenre().observe(viewLifecycleOwner) {
                        genreAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun onItemClick(genre:String){
        sharedViewModel.saveGenre(genre)
    }
}
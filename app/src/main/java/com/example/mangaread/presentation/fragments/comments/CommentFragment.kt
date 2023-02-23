package com.example.mangaread.presentation.fragments.comments

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentCommentBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.CommentAdapter
import com.example.mangaread.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class CommentFragment : BaseFragment<MainViewModel,FragmentCommentBinding>(R.layout.fragment_comment) {

    override val binding: FragmentCommentBinding by viewBinding(FragmentCommentBinding::bind)
    override val viewModel: MainViewModel by viewModel()
    private val commentAdapter = CommentAdapter()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun initialize() {
        super.initialize()
        binding.commentRv.adapter = commentAdapter
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.commentState.collect {
                    sharedViewModel.id.observe(viewLifecycleOwner){ id->
                        viewModel.fetchComments(id).observe(viewLifecycleOwner){ comments->
                            commentAdapter.submitList(comments)
                        }
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        super.setupListeners()
        with(binding) {

            btnAddComment.setOnClickListener {
                addComment.visibility = View.VISIBLE
                btnAddComment.visibility = View.GONE
            }

            tvAddBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.commentState.collect {
                            sharedViewModel.id.observe(viewLifecycleOwner) { id ->
                                sharedViewModel.token.observe(viewLifecycleOwner) { token ->
                                    viewModel.addComment(
                                        "Bearer $token",
                                        id,
                                        edComment.text.toString().trim()
                                    ).observe(viewLifecycleOwner) {
                                        Log.e("added", "setupListeners: ${it.text}")
                                    }
                                }
                            }
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
            tvBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }
}
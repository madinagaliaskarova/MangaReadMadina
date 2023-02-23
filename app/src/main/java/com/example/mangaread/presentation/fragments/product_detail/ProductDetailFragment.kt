package com.example.mangaread.presentation.fragments.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentProductDetailBinding
import com.example.mangaread.domain.utils.extensions.loadImage
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.CommentAdapter
import com.example.mangaread.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment :
    BaseFragment<MainViewModel, FragmentProductDetailBinding>(R.layout.fragment_product_detail) {

    override val binding: FragmentProductDetailBinding by viewBinding(FragmentProductDetailBinding::bind)
    private val sharedViewModel:SharedViewModel by activityViewModels()

    override val viewModel: MainViewModel by viewModel()
    private val commentAdapter = CommentAdapter()

    override fun initialize() {
        super.initialize()
        binding.rvProduct.adapter = commentAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mangaRequestById()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun mangaRequestById() {
        sharedViewModel.id.observe(viewLifecycleOwner) { id ->
            viewModel.fetchMangaById(id).observe(viewLifecycleOwner) { manga ->
                with(binding) {
                    mangaName.text = manga.ru_name
                    ratingTxt.text = manga.rating.toString()
                    mangaImg.loadImage(manga.image)
                    descTxt.text = manga.description
                    createdTxt.text = manga.created_at
                    likeTxt.text = manga.likes.toString()
                    yearTxt.text = manga.issue_year.toString()
                    typeTxt.text = manga.type
                    sharedViewModel.genre__title.observe(viewLifecycleOwner) { genre->
                        genreTxt.text = genre.toString()
                    }
                }
            }
        }
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.commentState.collect {
                    sharedViewModel.id.observe(viewLifecycleOwner) {
                        viewModel.fetchComments(it).observe(viewLifecycleOwner){ comments->
                            commentAdapter.submitList(comments)
                        }
                    }
                }
            }
        }
    }

    override fun setupListeners() = with(binding) {
        super.setupListeners()
        tvAddComment.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_to_commentFragment)
        }
        tvBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
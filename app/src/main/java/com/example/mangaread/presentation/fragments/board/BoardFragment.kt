package com.example.mangaread.presentation.fragments.board

import android.animation.ObjectAnimator
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.data.local.Prefs
import com.example.mangaread.databinding.FragmentBoardBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class BoardFragment : BaseFragment<MainViewModel,FragmentBoardBinding>(R.layout.fragment_board) {

    override val binding: FragmentBoardBinding by viewBinding(FragmentBoardBinding::bind)
    private var prefs: Prefs? = null
    override val viewModel: MainViewModel by viewModel()

    override fun initialize() {
        super.initialize()
        initAnim()

    }

    private fun initAnim() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                delay(2000)

                binding.btnSignIn.visibility = View.VISIBLE
                binding.btnSignUp.visibility = View.VISIBLE

                ObjectAnimator.ofFloat(binding.iconIv, "translationY", -125f).apply {
                    duration = 600
                    start()
                }
                ObjectAnimator.ofFloat(binding.btnUpAnim, "translationY", -125f).apply {
                    duration = 600
                    start()
                }
            }
        }

    }

    override fun setupListeners() {
        super.setupListeners()
        prefs = Prefs(requireContext())

        with(binding) {
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.fragment_home)
                prefs!!.saveState()
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.fragment_home)
                prefs!!.saveState()
            }
        }
    }
}
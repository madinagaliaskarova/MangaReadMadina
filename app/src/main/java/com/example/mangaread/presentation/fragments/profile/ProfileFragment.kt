package com.example.mangaread.presentation.fragments.profile

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentProfileBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class  ProfileFragment : BaseFragment<MainViewModel,FragmentProfileBinding>(R.layout.fragment_profile) {

    override val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override val viewModel: MainViewModel by viewModel()

    override fun setupListeners() {
        super.setupListeners()
        binding.textDashboard.setOnClickListener {
//          /  findNavController().navigate(R.id.authActivity)
        }
       binding.textToBoard.setOnClickListener {
//           findNavController().navigate(R.id.boardFragment)
       }
    }


}
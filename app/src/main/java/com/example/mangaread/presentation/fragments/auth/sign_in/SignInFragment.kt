package com.example.mangaread.presentation.fragments.auth.sign_in

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentSignInBinding
import com.example.mangaread.presentation.activity.AuthViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment :
    BaseFragment<AuthViewModel, FragmentSignInBinding>(R.layout.fragment_sign_in) {

    override val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel: AuthViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun setupListeners() {
        super.setupListeners()

        binding.btnEnter.setOnClickListener {
            viewModel.signIn(binding.usernameEd.text.toString().trim(),
                binding.passwordEd.text.toString().trim()).observe(viewLifecycleOwner) {
                if (it.status == 200) {
                    Toast.makeText(requireContext(), "Welcome ${it.user} ", Toast.LENGTH_SHORT)
                        .show()
                    sharedViewModel.saveToken(it.access)
                    sharedViewModel.saveRefresh(it.refresh)
                    Log.e("token signin", "setupListeners: ${it.access}")
                } else {
                    Toast.makeText(requireContext(),
                        "User not found, try again body!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
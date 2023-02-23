package com.example.mangaread.presentation.fragments.home

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mangaread.R
import com.example.mangaread.databinding.FragmentHomeBinding
import com.example.mangaread.presentation.activity.MainViewModel
import com.example.mangaread.presentation.activity.SharedViewModel
import com.example.mangaread.presentation.adapters.FragmentPagerAdapter
import com.example.mangaread.presentation.base.BaseFragment
import com.example.mangaread.presentation.fragments.auth.sign_in.SignInFragment
import com.example.mangaread.presentation.fragments.auth.sign_up.SignUpFragment
import com.example.mangaread.presentation.fragments.home.allmanga.AllMangaFragment
import com.example.mangaread.presentation.fragments.home.tophundred.TopHundredFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<MainViewModel,FragmentHomeBinding>(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: MainViewModel by viewModel()
    private val sharedViewModel:SharedViewModel by activityViewModels()

    private val fragmentList = listOf(
        AllMangaFragment(),
        TopHundredFragment()
    )
    private val fragmentTitles = listOf(
        "Вся манга",
        "Топ 100"
    )

    private val authFragmentList = listOf(
        SignInFragment(),
        SignUpFragment()
    )
    private val authFragmentTitles = listOf(
        "Вход",
        "Регистрация"
    )


    override fun initialize() {
        super.initialize()
        AuthTabs()
        MangaTabs()
    }

    private fun AuthTabs() {
        val adapter = FragmentPagerAdapter(requireActivity(), authFragmentList)
        binding.viewPagerAuth.adapter = adapter
        TabLayoutMediator(binding.tabLayoutAuth, binding.viewPagerAuth) { tab: TabLayout.Tab, i: Int ->
            tab.text = authFragmentTitles[i]
        }.attach()
    }

    private fun MangaTabs() {
        val adapter = FragmentPagerAdapter(requireActivity(), fragmentList)
        binding.viewPagerManga.adapter = adapter
        TabLayoutMediator(
            binding.tabLayoutManga,
            binding.viewPagerManga) {
                tab: TabLayout.Tab, i: Int -> tab.text = fragmentTitles[i]
        }.attach()
    }
    override fun setupListeners() {
        super.setupListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.token.observe(viewLifecycleOwner) { token->
                    if (token.isNotEmpty()) {
                        with(binding) {
                            viewPagerAuth.visibility = View.GONE
                            tabLayoutAuth.visibility = View.GONE

                            viewPagerManga.visibility = View.VISIBLE
                            tabLayoutManga.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
}
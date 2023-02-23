package com.example.mangaread.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mangaread.R
import com.example.mangaread.data.local.Prefs
import com.example.mangaread.databinding.ActivityMainBinding
import com.example.mangaread.presentation.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()
    private var prefs: Prefs? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.fragment_home, R.id.fragment_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        prefs = Prefs(this)

        if (!prefs?.isShown()!!){
            navController.navigate(R.id.boardFragment)
        }
        
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        navController.addOnDestinationChangedListener {
                navController: NavController,
                navDestination:
                NavDestination,
                bundle: Bundle?,
                ->

            with(binding) {
                val fragments = listOf(R.id.fragment_home,
                    R.id.commentFragment,
                    R.id.productDetailFragment,
                    R.id.boardFragment)

                if (fragments.contains(navDestination.id)) {
                    navView.visibility = View.GONE
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun initListener() {
        super.initListener()


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

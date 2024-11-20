package com.example.plantsapp.Views.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.plantsapp.R
import com.example.plantsapp.ViewModels.GardenViewModel
import com.example.plantsapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var gardenViewModel: GardenViewModel

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation()
        getAllGardens()
    }

    private fun initNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_bottom_nav)
                as NavHostFragment
        navController = navHostFragment.navController
        Log.d("MainFragment", "Current Destination: ${navController.currentDestination?.label}")
        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        binding.bottomBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.shopFragment, R.id.gardenFragment, R.id.profileFragment -> {
                    binding.bottomBar.visibility = View.VISIBLE // Show Bottom Navigation
                }
                else -> {
                    binding.bottomBar.visibility = View.GONE // Hide Bottom Navigation for other fragments
                }
            }
        }
        // Manually handle navigation actions
        binding.bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.garden -> {
                    navController.navigate(R.id.gardenFragment)
                    true
                }
                R.id.cart -> {
                    navController.navigate(R.id.shopFragment)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun getAllGardens(){
       gardenViewModel.viewModelScope.launch {
           gardenViewModel.getAllGardens()
       }

    }
}
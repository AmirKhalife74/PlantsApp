package com.example.plantsapp.views.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantsapp.databinding.FragmentAuthBinding
import com.example.plantsapp.views.auth.adapter.AuthPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthFragment : Fragment(),AuthNavigationListener {

    lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()

    }
    private fun init(){
        binding.apply {
            authViewpager.adapter = AuthPagerAdapter(this@AuthFragment)

            // Handle communication between fragments
            setFragmentListeners()
        }

    }
    private fun observe(){}
    private fun listen(){}

    private fun setFragmentListeners() {
        binding.apply {
            parentFragmentManager.setFragmentResultListener("navigateToRegister", this@AuthFragment) { _, _ ->
                authViewpager.setCurrentItem(0, true)
            }
            parentFragmentManager.setFragmentResultListener("navigateToLogin", this@AuthFragment) { _, _ ->
                authViewpager.setCurrentItem(1, true)
            }
        }

    }

    override fun navigateTo(position: Int) {
        binding.apply {
            authViewpager.setCurrentItem(position, true)
        }

    }

}
package com.example.plantsapp.views.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.data.model.auth.LoginRequest
import com.example.plantsapp.R
import com.example.plantsapp.databinding.FragmentLoginBinding
import com.example.plantsapp.utils.Env
import com.example.plantsapp.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()

    }

    private fun init() {

    }

    private fun listen() {
        binding.apply {
            btnSubmitLogin.setOnClickListener {
                userViewModel.viewModelScope.launch {
                    val username = edtUsername.text.toString()
                    val password = edtPassword.text.toString()
                    val loginRequest = LoginRequest(username = username,password = password)
                    userViewModel.login(loginRequest)
                }
            }
        }
    }

    private fun observe() {
        userViewModel.viewModelScope.launch {
            userViewModel.loginResponse.observe(viewLifecycleOwner){ value ->
                value?.let {
                    if (value.accessToken.isNotEmpty() && value.refreshToken.isNotEmpty())
                    {
                        Env.store.setString("access_token",value.accessToken)
                        Env.store.setString("refresh_token",value.refreshToken)
                        Env.store.setBoolean("isLogin",true)
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                }
            }
        }
    }
}
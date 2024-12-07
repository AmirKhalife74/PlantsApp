package com.example.plantsapp.views.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.data.model.auth.RegisterRequest
import com.example.plantsapp.databinding.FragmentRegisterBinding
import com.example.plantsapp.viewModels.UserViewModel
import com.example.utils.UserRole
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment(private val listener: AuthNavigationListener) : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        listen()
        observe()
    }

    private fun init() {
        binding.apply {
            tvLogin.setOnClickListener {
                listener.navigateTo(0)
            }
        }
    }

    private fun listen() {
        binding.apply {
            btnRegister.setOnClickListener {
                if (edtEmail.text.isBlank()) {
                    edtEmail.error = "لطفا ایمیل را وارد کنید !"
                } else if (edtUsername.text.isBlank()) {
                    edtUsername.error = "لطفا نام کاربری را وارد کنید !"
                } else if (edtPassword.text.isBlank()) {
                    edtPassword.error = "لطفا رمز عبور را وارد کنید !"
                } else if (edtRePass.text.isBlank()) {
                    edtRePass.error = "لطفا رمز عبور را دوباره وارد کنید !"
                } else if (edtPassword.text.toString() != edtRePass.text.toString()) {
                    edtPassword.error = "تکرار رمز عبور صحیح نیست !"
                } else {
                    val registerRequest = RegisterRequest(
                        username = edtUsername.text.toString(),
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString(),
                        edtRePass.text.toString(),
                        UserRole.USER
                    )
                    userViewModel.viewModelScope.launch {
                        userViewModel.register(registerRequest)
                    }
                }
            }
        }
    }

    private fun observe() {
        userViewModel.registerResponse.observe(viewLifecycleOwner)
        {
            it?.let {
                userViewModel.clearRegisterResponse()
                binding.apply {
                    edtPassword.setText("")
                    edtEmail.setText("")
                    edtUsername.setText("")
                    edtRePass.setText("")
                }
                listener.navigateTo(0)
            }
        }
    }
}
package com.example.plantsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantsapp.data.roomDb.user.User
import com.example.plantsapp.data.model.auth.LoginRequest
import com.example.plantsapp.data.model.auth.RegisterRequest
import com.example.plantsapp.data.repositories.UserRepository
import com.example.plantsapp.data.model.auth.LoginResponse
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    private var _loginResponse = MutableLiveData<LoginResponse?>(null)
    val loginResponse : MutableLiveData<LoginResponse?> get() = _loginResponse
    //fun clearLoginResponse() = _loginResponse.postValue(null)

    suspend fun login(loginRequest: LoginRequest)
    {
        userRepository.login(loginRequest).let { response ->
            if (response.isSuccessful)
            {
                response.body()?.let { data ->
                    if (data.status == 200)
                    {
                        data.data?.let {
                            _loginResponse.postValue(data.data)
                        }

                    }
                }
            }
        }
    }

    private var _registerResponse = MutableLiveData<User?>(null)
    val registerResponse : MutableLiveData<User?> get() = _registerResponse
    fun clearRegisterResponse() = _registerResponse.postValue(null)

    suspend fun register(registerRequest: RegisterRequest)
    {
        userRepository.register(registerRequest).let { response ->
            if (response.isSuccessful)
            {
                response.body()?.let { data ->
                    if (data.status == 200)
                    {
                        data.data?.let {
                            _registerResponse.postValue(data.data)
                        }
                    }

                }
            }

        }
    }

}
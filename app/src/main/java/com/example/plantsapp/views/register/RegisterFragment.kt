package com.example.plantsapp.views.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantsapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    lateinit var binding:FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        listen()
        observe()
    }

    private fun init(){

    }
    private fun listen(){

    }
    private fun observe(){

    }
}
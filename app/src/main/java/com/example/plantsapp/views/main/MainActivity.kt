package com.example.plantsapp.views.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.plantsapp.viewModels.MainViewModel
import com.example.plantsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //val config = Configuration(resources.configuration)
        //config.setLayoutDirection(Locale("fa")) // Replace "fa" with your desired RTL language code
        //resources.updateConfiguration(config, resources.displayMetrics)
        binding = ActivityMainBinding.inflate(layoutInflater)
        loadData()
        setContentView(binding.root)
    }



    private fun loadData()
    {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getPlants()
        }

    }


}
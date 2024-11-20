package com.example.plantsapp.Views.Main

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.util.TypedValue.*
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.ViewModels.MainViewModel
import com.example.plantsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
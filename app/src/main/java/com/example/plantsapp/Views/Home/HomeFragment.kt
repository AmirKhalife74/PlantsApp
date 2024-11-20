package com.example.plantsapp.Views.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.R
import com.example.plantsapp.ViewModels.MainViewModel
import com.example.plantsapp.Views.Home.Adapter.HomePlantsAdapter
import com.example.plantsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel:MainViewModel

    lateinit var plants:MutableList<Plant?>
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    private fun init()
    {

    }
    private fun listen()
    {

    }
    private fun observe()
    {
        viewModel.plantList.observe(viewLifecycleOwner){
            plants = it
            setRc()
        }
    }

    private fun setRc()
    {
        binding.apply {
            rcPlantsHome.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
            val adapter = HomePlantsAdapter(plants,findNavController(),requireContext())
            rcPlantsHome.adapter = adapter
        }
    }
}
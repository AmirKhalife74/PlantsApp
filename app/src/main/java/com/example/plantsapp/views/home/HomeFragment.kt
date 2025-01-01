package com.example.plantsapp.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.plantsapp.data.Plant
import com.example.plantsapp.viewModels.MainViewModel
import com.example.plantsapp.views.home.Adapter.HomePlantsAdapter
import com.example.plantsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var plants: MutableList<Plant?>
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    private fun init() {
        viewModel.viewModelScope.launch {
            viewModel.getPlants()
        }

    }

    private fun listen() {

    }

    private fun observe() {
        viewModel.plantList.observe(viewLifecycleOwner) {
            it?.let {
                plants = it.toMutableList()
                setRc()
            }

        }
    }

    private fun setRc() {
        binding.apply {
            rcPlantsHome.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            val adapter = HomePlantsAdapter(plants, findNavController(), requireContext())
            rcPlantsHome.adapter = adapter
        }
    }
}
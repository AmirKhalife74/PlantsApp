package com.example.plantsapp.views.garden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.plantsapp.R
import com.example.plantsapp.data.Garden
import com.example.plantsapp.utils.RecyclerTouchListener
import com.example.plantsapp.viewModels.GardenViewModel
import com.example.plantsapp.views.garden.adapter.GardenItemsMainAdapter
import com.example.plantsapp.databinding.FragmentGardenBinding
import com.example.plantsapp.utils.createGarden
import com.example.plantsapp.utils.showDialogOneButton
import com.example.plantsapp.viewModels.ReminderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GardenListFragment : Fragment() {

    private lateinit var binding: FragmentGardenBinding

    @Inject
    lateinit var gardenViewModel: GardenViewModel

    @Inject
    lateinit var reminderViewModel: ReminderViewModel

    lateinit var gardenList: MutableList<Garden>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listen()
        observe()
    }

    private fun init() {
        gardenViewModel.viewModelScope.launch {
            gardenViewModel.getAllGardens()
            binding.apply {
                progressGardens.visibility = View.VISIBLE
                lytNoData.visibility = View.GONE
            }
        }

    }

    private fun listen() {
        binding.apply {
            imgAddGarden.setOnClickListener {
                createGarden { newGarden ->
                    newGarden.let {
                        gardenViewModel.viewModelScope.launch {
                            gardenViewModel.createGarden(newGarden)
                            showDialogOneButton("باغچه با موفقبت ساخته شد !")
                            gardenViewModel.getAllGardens()

                        }
                    }
                }
            }
        }
    }

    private fun observe() {
        binding.apply {
            gardenViewModel.viewModelScope.launch {
                gardenViewModel.getAllGardensResponse.observe(viewLifecycleOwner) { it ->
                    progressGardens.visibility = View.GONE
                    it?.let {
                        lytData.visibility = View.VISIBLE
                        lytNoData.visibility = View.GONE
                        if (it.isNotEmpty()) {
                            val addGarden =
                                Garden(null, "+ باغچه جدید","", mutableListOf(), R.color.dark_main)
                            gardenList = it
                            gardenList.add(gardenList.size, addGarden)
                            val adapter = GardenItemsMainAdapter(
                                context = requireContext(),
                                gardens = it,
                            )
                            rcGardens.adapter = adapter
                            rcGardens.addOnItemTouchListener(
                                RecyclerTouchListener(
                                    context,
                                    rcGardens,
                                    object : RecyclerTouchListener.OnItemClickListener {
                                        override fun onItemClick(view: View?, position: Int) {
                                            if (position == gardenList.size) {
                                                createGarden { newGarden ->
                                                    gardenViewModel.viewModelScope.launch {
                                                        gardenViewModel.createGarden(newGarden)
                                                    }
                                                }
                                            } else {
                                                gardenViewModel.viewModelScope.launch {
                                                    gardenList[position].id?.let { it1 ->
                                                        gardenViewModel.getGardenById(
                                                            it1
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        override fun onLongItemClick(view: View?, position: Int) {

                                        }
                                    })
                            )

                        } else {
                            lytData.visibility = View.GONE
                            lytNoData.visibility = View.VISIBLE
                            progressGardens.visibility = View.GONE
                        }
                    }
                }
            }
        }

    }
}
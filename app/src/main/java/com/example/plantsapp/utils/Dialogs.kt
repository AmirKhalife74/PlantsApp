package com.example.plantsapp.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantsapp.R
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.databinding.DialogAddGardenBinding
import com.example.plantsapp.views.plant.adapter.GardenListAdapter
import com.example.plantsapp.databinding.DialogAddToGardenBinding
import com.example.plantsapp.databinding.DialogOneButtonBinding
import com.example.plantsapp.utils.Env.selectedChipColor

fun Fragment.addToGarden(
    gardens: List<Garden>?,
    plant: Plant,
    operator: (type: Int, garden: Garden?) -> Unit
) {
    view.let {
        view?.let {
            val binding =
                DialogAddToGardenBinding.inflate(LayoutInflater.from(activity!!))
            val dialog = Dialog(activity!!)
            dialog.setContentView(binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setGravity(Gravity.BOTTOM)

            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)

            binding.apply {
                var selectedGarden: Garden? = null
                if (!gardens.isNullOrEmpty()) {
                    val adapter = GardenListAdapter(gardens, requireContext())
                    rcGardens.layoutManager = LinearLayoutManager(requireContext())
                    rcGardens.adapter = adapter
                    rcGardens.visibility = View.VISIBLE
                }

                btnClose.setOnClickListener {
                    dialog.dismiss()
                }
                imgAdd.setOnClickListener {
                    dialog.dismiss()
                    operator(0, null)
                }

                btnAcceptToAddGarden.setOnClickListener {
                    if (selectedGarden != null) {
                        operator(1, selectedGarden)
                    }
                }
                rcGardens.addOnItemTouchListener(
                    RecyclerTouchListener(
                        context,
                        rcGardens,
                        object : RecyclerTouchListener.OnItemClickListener {
                            override fun onItemClick(view: View?, position: Int) {
                                selectedGarden = gardens?.get(position)
                                if (gardens != null) {

                                    selectedGarden?.plants?.add(plant)
                                    dialog.dismiss()
                                    logger(selectedGarden.toString())
                                }
                            }

                            override fun onLongItemClick(view: View?, position: Int) {

                            }
                        })
                )

                dialog.show()

            }

        }
    }
}

fun Fragment.createGarden(

    operator: (garden: Garden) -> Unit
) {
    view.let {
        view?.let {
            val binding =
                DialogAddGardenBinding.inflate(LayoutInflater.from(activity!!))
            val dialog = Dialog(activity!!)
            dialog.setContentView(binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setGravity(Gravity.BOTTOM)

            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)
            dialog.show()
            binding.apply {
                val chips = listOf(chipRed, chipGreen, chipPurple, chipYellow, chipBlue)
                chips.forEach { chip ->
                    val colorResId = when (chip.id) {
                        R.id.chip_green -> R.color.garden_green
                        R.id.chip_yellow -> R.color.garden_yellow
                        R.id.chip_red -> R.color.garden_red
                        R.id.chip_blue -> R.color.garden_blue
                        R.id.chip_purple -> R.color.garden_purple
                        else -> android.R.color.white
                    }
                    resetChipColors(chips, requireContext())
                    setupChipClickListener(chip, colorResId, chips, requireContext())

                }
                btnCreateGarden.setOnClickListener {
                    if (selectedChipColor != 0 && edtGardenName.text.toString() != "") {
                        val newGarden = Garden(
                            null,
                            edtGardenName.text.toString(),
                            edtGardenDesc.text.toString(),
                            mutableListOf(),
                            selectedChipColor
                        )
                        operator(newGarden)
                        dialog.dismiss()
                    } else {
                        edtGardenName.error = "لطفا نام و رنگ باغچه را انتخاب کنید !"
                    }
                }
                btnCancel.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
}

fun Fragment.showDialogOneButton(
    message: String
) {
    view.let {
        view?.let {
            val binding =
                DialogOneButtonBinding.inflate(LayoutInflater.from(activity!!))
            val dialog = Dialog(activity!!)
            dialog.setContentView(binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setGravity(Gravity.BOTTOM)

            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)
            dialog.show()
            binding.apply {
                tvMessage.text = message
                btnAccept.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
}



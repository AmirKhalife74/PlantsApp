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
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.views.plant.adapter.GardenListAdapter
import com.example.plantsapp.databinding.DialogAddToGardenBinding

fun Fragment.addToGarden(
    gardens: List<Garden>?,
    plant: Plant,
    operator: (type: Int, garden: Garden, gardenName: String) -> Unit
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
            );
            dialog.window!!.setGravity(Gravity.BOTTOM)

            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)

            binding.apply {
                if (!gardens.isNullOrEmpty()) {
                    val adapter = GardenListAdapter(gardens, requireContext())
                    rcGardens.layoutManager = LinearLayoutManager(requireContext())
                    rcGardens.adapter = adapter
                    rcGardens.visibility = View.VISIBLE
                }

                btnClose.setOnClickListener {
                    dialog.dismiss()
                }

                btnAcceptToAddGarden.setOnClickListener {
                    if (edtAddNewGarden.visibility == View.VISIBLE) {
                        if (edtAddNewGarden.text.toString().isEmpty())
                        {
                            edtAddNewGarden.error = "Please Enter Garden Name"
                        }else{
                            operator(0, gardens!![0], edtAddNewGarden.text.toString())
                            dialog.dismiss()
                        }


                    }
                }
                rcGardens.addOnItemTouchListener(
                    RecyclerTouchListener(
                        context,
                        rcGardens,
                        object : RecyclerTouchListener.OnItemClickListener {
                            override fun onItemClick(view: View?, position: Int) {
                                val item = gardens?.get(position)
                                if (position == 0) {
                                    edtAddNewGarden.visibility = View.VISIBLE
                                    rcGardens.visibility = View.GONE
                                    textView.text = "add new Item"

                                } else if (gardens != null) {
                                    if (gardens.get(position) != null) {

                                        item?.plants?.add(plant)
                                        dialog.dismiss()
                                        logger(item.toString())
                                        if (item != null) {
                                            operator(1, item, "")
                                        }

                                    }
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
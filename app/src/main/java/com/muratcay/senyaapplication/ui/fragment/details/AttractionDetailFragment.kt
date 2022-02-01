package com.muratcay.senyaapplication.ui.fragment.details

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.databinding.FragmentAttractionDetailBinding
import com.muratcay.senyaapplication.ui.fragment.BaseFragment

class AttractionDetailFragment :
    BaseFragment<FragmentAttractionDetailBinding>(FragmentAttractionDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        dataLoad()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun dataLoad() {
        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.apply {
                titleTextView.text = attraction.title
                headerEpoxyRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                monthsToVisitTextView.text = attraction.months
                numberOfFactsTextView.text = "${attraction.facts.size} facts"
                descriptionTextView.text = attraction.description
                binding.headerEpoxyRecyclerView.setControllerAndBuildModels(
                    HeaderEpoxyController(
                        attraction.url_image
                    )
                )
                LinearSnapHelper().attachToRecyclerView(binding.headerEpoxyRecyclerView)
                binding.indicator.attachToRecyclerView(binding.headerEpoxyRecyclerView)
                numberOfFactsTextView.setOnClickListener {
                    val stringBuilder = StringBuilder("")
                    attraction.facts.forEach {
                        stringBuilder.append("\u2022 $it \n\n")
                    }
                    val message =
                        stringBuilder.toString()
                            .substring(0, stringBuilder.toString().lastIndexOf("\n\n"))
                    AlertDialog.Builder(requireContext()).setTitle("${attraction.title} Facts")
                        .setMessage(message)
                        .setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                        }.setNegativeButton("No!") { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                }
            }
        }
    }
}
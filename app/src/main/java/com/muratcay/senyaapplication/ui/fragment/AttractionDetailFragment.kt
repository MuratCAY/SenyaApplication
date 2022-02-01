package com.muratcay.senyaapplication.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment :
    BaseFragment<FragmentAttractionDetailBinding>(FragmentAttractionDetailBinding::inflate) {
    /*
   private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataLoad()
        setHasOptionsMenu(true)
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
            Glide.with(requireActivity()).load(attraction.imageUrls)
                .error(R.drawable.ic_launcher_background).into(binding.headerImageView)
            binding.apply {
                titleTextView.text = attraction.title
                monthsToVisitTextView.text = attraction.months
                numberOfFactsTextView.text = "${attraction.facts.size} facts"
                descriptionTextView.text = attraction.description
                numberOfFactsTextView.setOnClickListener {
                    val stringBuilder = StringBuilder("")
                    attraction.facts.forEach {
                        stringBuilder.append("\u2022 $it \n\n")
                    }
                    val message =
                        stringBuilder.toString()
                            .substring(0, stringBuilder.toString().lastIndexOf("\n\n"))
                    AlertDialog.Builder(requireContext())
                        .setTitle("${attraction.title} Facts")
                        .setMessage(message)
                        .setPositiveButton("Ok") { dialog, which ->
                            dialog.dismiss()
                        }.setNegativeButton("No!") { dialog, which ->
                            dialog.dismiss()
                        }.show()
                }
            }
        }
    }
}
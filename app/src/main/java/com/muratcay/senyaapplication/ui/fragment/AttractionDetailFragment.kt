package com.muratcay.senyaapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.data.Attraction
import com.muratcay.senyaapplication.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment :
    BaseFragment<FragmentAttractionDetailBinding>(FragmentAttractionDetailBinding::inflate) {

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()

    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataLoad()
    }

    @SuppressLint("SetTextI18n")
    private fun dataLoad() {
        binding.titleTextView.text = attraction.title
        Glide.with(requireActivity()).load(attraction.imageUrls).error(R.drawable.ic_launcher_background).into(binding.headerImageView)
        binding.monthsToVisitTextView.text = attraction.months
        binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"
        binding.descriptionTextView.text = attraction.description
        binding.numberOfFactsTextView.setOnClickListener {
            // TODO:
        }
    }
}
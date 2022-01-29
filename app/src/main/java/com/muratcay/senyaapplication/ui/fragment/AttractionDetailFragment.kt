package com.muratcay.senyaapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.muratcay.senyaapplication.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>(FragmentAttractionDetailBinding::inflate) {

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.text = safeArgs.attractionId
    }

}
package com.muratcay.senyaapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment : Fragment() {
    private val binding: FragmentAttractionDetailBinding by lazy {
        FragmentAttractionDetailBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}
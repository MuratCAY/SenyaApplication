package com.muratcay.senyaapplication.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.muratcay.senyaapplication.databinding.FragmentHomeBinding
import com.muratcay.senyaapplication.ui.fragment.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeFragmentAdapter { attractionId ->
            val navDirections =
                HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
            navController.navigate(navDirections)
        }
        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        homeAdapter.setData(attraction)
    }

}
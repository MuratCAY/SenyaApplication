package com.muratcay.senyaapplication.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.databinding.FragmentHomeBinding
import com.muratcay.senyaapplication.ui.fragment.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = HomeFragmentController { attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }
        binding.apply {
            epoxyRecyclerView.setController(epoxyController)
            epoxyRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                )
            )
        }
        epoxyController.isLoading = true
        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions
        }
    }
}
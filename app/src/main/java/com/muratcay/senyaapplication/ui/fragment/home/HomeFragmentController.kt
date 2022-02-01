package com.muratcay.senyaapplication.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.data.Attraction
import com.muratcay.senyaapplication.databinding.ViewHolderAttractionBinding
import com.muratcay.senyaapplication.ui.exopy.LoadingEpoxyModel
import com.muratcay.senyaapplication.ui.exopy.ViewBindingKotlinModel

class HomeFragmentController(private val onClickedCallback: (String) -> Unit) : EpoxyController() {

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            monthToVisitTextView.text = attraction.months

            Glide.with(root.context).load(attraction.imageUrls)
                .error(R.drawable.ic_launcher_background).into(headerImageView)

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
            // requestModelBuild() = bu notifyDataSetChanged() ile aynı işlevi görüyor.
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            // TODO: show empty state
            return
        }
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }
    }
}

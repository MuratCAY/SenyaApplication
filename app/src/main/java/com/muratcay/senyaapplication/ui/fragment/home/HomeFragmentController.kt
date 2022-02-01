package com.muratcay.senyaapplication.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.data.Attraction
import com.muratcay.senyaapplication.databinding.EpoxyModelHeaderBinding
import com.muratcay.senyaapplication.databinding.ViewHolderAttractionBinding
import com.muratcay.senyaapplication.ui.exopy.LoadingEpoxyModel
import com.muratcay.senyaapplication.ui.exopy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(private val onClickedCallback: (String) -> Unit) : EpoxyController() {

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            monthToVisitTextView.text = attraction.months
            if (attraction.url_image.isNotEmpty()) {
                Picasso.get().load(attraction.url_image[0]).error(R.drawable.ic_launcher_background)
                    .into(headerImageView)
            }
            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }

    data class HeaderEpoxyModel(
        val headerText: String
    ) : ViewBindingKotlinModel<EpoxyModelHeaderBinding>(R.layout.epoxy_model_header) {
        override fun EpoxyModelHeaderBinding.bind() {
            headerTextView.text = headerText
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
        val firstGroup =
            attractions.filter { it.title.startsWith("s", true) || it.title.startsWith("D", true) }
        HeaderEpoxyModel("Something special").id("unique").addIf(firstGroup.size == 3, this)
        HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)

        firstGroup.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }
        HeaderEpoxyModel("All Attractions").id("header_2").addTo(this)
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }
    }
}

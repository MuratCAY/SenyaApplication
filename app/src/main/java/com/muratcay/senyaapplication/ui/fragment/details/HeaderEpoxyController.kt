package com.muratcay.senyaapplication.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.databinding.ModelHeaderImageBinding
import com.muratcay.senyaapplication.ui.exopy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HeaderEpoxyController(private val imageUrls: List<String>) : EpoxyController() {
    override fun buildModels() {
        imageUrls.forEachIndexed { index, url ->
            HeaderImageEpoxyModel(url).id(index).addTo(this)
        }
    }

    inner class HeaderImageEpoxyModel(
        private val imageUrl: String
    ) : ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image) {

        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(modelImageView)
        }

    }
}
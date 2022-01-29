package com.muratcay.senyaapplication.ui.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.data.Attraction
import com.muratcay.senyaapplication.databinding.ViewHolderAttractionBinding

class HomeFragmentAdapter(private val onClickedCallback: (String) -> Unit) :
    RecyclerView.Adapter<HomeFragmentAdapter.AttractionViewHolder>() {

    private val attractions = ArrayList<Attraction>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(attractions: List<Attraction>) {
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(val itemBinding: ViewHolderAttractionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(attraction: Attraction, onClicked: (String) -> Unit) {
            //  Picasso.get().load(attraction.imageUrls).into(itemBinding.headerImageView)
            itemBinding.titleTextView.text = attraction.title
            itemBinding.monthToVisitTextView.text = attraction.months

            Glide.with(itemView.context).load(attraction.imageUrls)
                .error(R.drawable.ic_launcher_background).into(itemBinding.headerImageView)

            itemBinding.root.setOnClickListener {
                onClicked(attraction.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val view =
            ViewHolderAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.onBind(attractions[position], onClickedCallback)
    }

    override fun getItemCount() = attractions.size
}

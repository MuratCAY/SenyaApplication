package com.muratcay.senyaapplication.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcay.senyaapplication.data.Attraction
import org.w3c.dom.Attr

class AttractionsViewModel : ViewModel() {

    private val repository = AttractionsRepository()

    //Home Fragment
    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    //Attraction Detail Fragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        val attractionList = repository.parseAttractions(context)
        attractionListLiveData.value = attractionList
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }
}
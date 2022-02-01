package com.muratcay.senyaapplication.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcay.senyaapplication.data.Attraction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionsViewModel : ViewModel() {

    private val repository = AttractionsRepository()

    //Home Fragment
    val attractionListLiveData = MutableLiveData<ArrayList<Attraction>>()

    //Attraction Detail Fragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        viewModelScope.launch {
            delay(5000)
            val attractionList = repository.parseAttractions(context)
            attractionListLiveData.value = attractionList
        }
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }
}
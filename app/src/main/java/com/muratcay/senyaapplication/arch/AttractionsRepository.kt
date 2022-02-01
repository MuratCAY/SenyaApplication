package com.muratcay.senyaapplication.arch

import android.content.Context
import com.muratcay.senyaapplication.R
import com.muratcay.senyaapplication.data.Attraction
import com.muratcay.senyaapplication.data.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    fun parseAttractions(context: Context): ArrayList<Attraction> {
        val textFromFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionResponse> = moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions as ArrayList<Attraction>
    }
}
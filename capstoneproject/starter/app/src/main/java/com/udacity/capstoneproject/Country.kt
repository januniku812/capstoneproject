package com.udacity.capstoneproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(val id: String,
                   val countryCode: String,
                   val countryName: String,
                   val newConfirmed: String, val totalConfirmed: String, // confirmed cases
                   val newDeaths: String, val totalDeaths: String, // deaths
                   val newRecovered: String, val totalRecovered: String // recovered
                   ) : Parcelable

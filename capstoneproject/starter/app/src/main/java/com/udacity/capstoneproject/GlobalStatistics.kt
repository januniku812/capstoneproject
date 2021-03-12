package com.udacity.capstoneproject

data class GlobalStatistics(
    val newConfirmed: String, val totalConfirmed: String, // confirmed cases
    val newDeaths: String, val totalDeaths: String, // deaths
    val newRecovered: String, val totalRecovered: String // recovered
    )

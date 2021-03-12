package com.udacity.capstoneproject.api

import com.udacity.capstoneproject.Country
import com.udacity.capstoneproject.GlobalStatistics
import org.json.JSONObject

fun parseCountriesJson(jsonResult: JSONObject): ArrayList<Country> {
        val countriesJSONArray = jsonResult.getJSONArray("Countries")
        val countryList = ArrayList<Country>() // list of country objects to return
        for(i in 0 until countriesJSONArray.length()) {
            val countryJsonObject = countriesJSONArray.getJSONObject(i)
            // getting all the country attributes
            val id = countryJsonObject.getString("ID").toString()
            val countryCode = countryJsonObject.getString("CountryCode").toString()
            val countryName = countryJsonObject.getString("Country").toString()
            val newConfirmed = countryJsonObject.getInt("NewConfirmed").toString()
            val totalConfirmed = countryJsonObject.getInt("TotalConfirmed").toString()
            val newDeaths = countryJsonObject.getInt("NewDeaths").toString()
            val totalDeaths = countryJsonObject.getInt("TotalDeaths").toString()
            val newRecovered = countryJsonObject.getInt("NewRecovered").toString()
            val totalRecovered = countryJsonObject.getInt("TotalRecovered").toString()
            // adding a country object after parsing the country attributes
            countryList.add(Country(id, countryCode, countryName, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered))
        }
        return countryList
    }


fun parseGlobalJson(jsonResult: JSONObject): GlobalStatistics {
        val globalStatisticsJsonObject = jsonResult.getJSONObject("Global")

        // getting all the global attributes
        val newConfirmed = globalStatisticsJsonObject.getInt("NewConfirmed").toString()
        val totalConfirmed = globalStatisticsJsonObject.getInt("TotalConfirmed").toString()
        val newDeaths = globalStatisticsJsonObject.getInt("NewDeaths").toString()
        val totalDeaths = globalStatisticsJsonObject.getInt("TotalDeaths").toString()
        val newRecovered = globalStatisticsJsonObject.getInt("NewRecovered").toString()
        val totalRecovered = globalStatisticsJsonObject.getInt("TotalRecovered").toString()

        return GlobalStatistics(newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered)
}
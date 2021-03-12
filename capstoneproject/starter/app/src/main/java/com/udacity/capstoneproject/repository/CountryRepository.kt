package com.udacity.capstoneproject.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.capstoneproject.Country
import com.udacity.capstoneproject.GlobalStatistics
import com.udacity.capstoneproject.NetworkStatus
import com.udacity.capstoneproject.api.ApiService
import com.udacity.capstoneproject.api.parseCountriesJson
import com.udacity.capstoneproject.api.parseGlobalJson
import com.udacity.capstoneproject.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CountryRepository(val countryDatabase: CountryDatabase) {

    val latestCountryData: LiveData<List<Country>> =
        Transformations.map(countryDatabase.countryDao.getLatestCountryData()) {
            it.asDomainModel()
        }

    var latestGlobalStatistics: GlobalStatistics? = null

    var status: NetworkStatus? = null

    var globalStatisticsStatus: NetworkStatus? = null

    suspend fun refreshCountries() {
        status = NetworkStatus.LOADING
        withContext(Dispatchers.IO) {
            try {
                val countriesData = ApiService.CovidApi.retrofitService.getAllCountriesAndGlobalStatsData()
                val result = parseCountriesJson(JSONObject(countriesData))
                countryDatabase.countryDao.insertAll(*result.asDatabaseModel())
                status = NetworkStatus.DONE
                Log.d("Refresh Country Data", "Success")
            } catch (err: Exception) {
                status = NetworkStatus.ERROR
                Log.e("Failed: CountryRepFile", err.message.toString())
            }
        }
    }

    suspend fun refreshGlobalStats() {
        globalStatisticsStatus = NetworkStatus.LOADING
        withContext(Dispatchers.IO) {
            try {
                latestGlobalStatistics =  parseGlobalJson(JSONObject(ApiService.CovidApi.retrofitService.getAllCountriesAndGlobalStatsData()))
                globalStatisticsStatus = NetworkStatus.DONE
                Log.d("Refresh GlobalStat Data", "Success")
            } catch (err: Exception) {
                globalStatisticsStatus = NetworkStatus.ERROR
                Log.e("Failed: CountryRepFile", err.message.toString())
            }
        }
    }

}
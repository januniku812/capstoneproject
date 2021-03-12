package com.udacity.capstoneproject.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.udacity.capstoneproject.NetworkStatus
import com.udacity.capstoneproject.database.getDatabase
import com.udacity.capstoneproject.repository.CountryRepository
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
public class MainViewModel(application: Application) : AndroidViewModel(application) {

//    database
    private val database = getDatabase(application)
//    repository
    private val countryRepository = CountryRepository(database)

//  status of the data(e.g. if its loading than the progress bar should spin)
    private val _status = MutableLiveData<NetworkStatus>()
    val status: LiveData<NetworkStatus>
        get() = _status

    private val _globalStatisticsStatus = MutableLiveData<NetworkStatus>()
    val globalStatisticsStatus: LiveData<NetworkStatus>
        get() = _globalStatisticsStatus

    val countryList = countryRepository.latestCountryData

    val globalStatistics = countryRepository.latestGlobalStatistics


    init {
        viewModelScope.launch {
            countryRepository.refreshCountries()
            countryRepository.refreshGlobalStats()
            checkIfCountriesLoaded()
            checkIfGlobalStatsLoaded()
        }

    }


    private fun checkIfCountriesLoaded(){
        if(countryRepository.status == NetworkStatus.DONE){
            _status.value = NetworkStatus.DONE
        }
        else if(countryRepository.status == NetworkStatus.ERROR){
            _status.value = NetworkStatus.ERROR
        }
        else{
            _status.value = NetworkStatus.LOADING
        }
    }

    private fun checkIfGlobalStatsLoaded(){
        if(countryRepository.globalStatisticsStatus == NetworkStatus.DONE){
            _globalStatisticsStatus.value = NetworkStatus.DONE
        }
        else if(countryRepository.globalStatisticsStatus == NetworkStatus.ERROR){
            _globalStatisticsStatus.value = NetworkStatus.ERROR
        }
        else{
            _globalStatisticsStatus.value = NetworkStatus.LOADING
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }

}
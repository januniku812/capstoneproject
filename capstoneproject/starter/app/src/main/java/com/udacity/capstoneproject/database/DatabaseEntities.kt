package com.udacity.capstoneproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.capstoneproject.Country

@Entity(tableName = "countries")
data class DatabaseCountry constructor(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val countryCode: String,
    val countryName: String,
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeaths: String,
    val totalDeaths: String,
    val newRecovered: String,
    val totalRecovered: String
)

fun List<DatabaseCountry>.asDomainModel(): List<Country> {
    return map {
        Country(
            id = it.id,
            countryCode = it.countryCode,
            countryName = it.countryName,
            newConfirmed = it.newConfirmed,
            totalConfirmed = it.totalConfirmed,
            newDeaths = it.newDeaths,
            totalDeaths = it.totalDeaths,
            newRecovered = it.newRecovered,
            totalRecovered = it.totalRecovered
        )
    }
}

fun List<Country>.asDatabaseModel(): Array<DatabaseCountry> {
    return map {
        DatabaseCountry(
            id = it.id,
            countryCode = it.countryCode,
            countryName = it.countryName,
            newConfirmed = it.newConfirmed,
            totalConfirmed = it.totalConfirmed,
            newDeaths = it.newDeaths,
            totalDeaths = it.totalDeaths,
            newRecovered = it.newRecovered,
            totalRecovered = it.totalRecovered
        )
    }.toTypedArray()
}

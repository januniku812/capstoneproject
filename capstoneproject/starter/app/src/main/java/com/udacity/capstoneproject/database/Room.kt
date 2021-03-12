package com.udacity.capstoneproject.database

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getLatestCountryData(): LiveData<List<DatabaseCountry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg country: DatabaseCountry)


}

@Database(entities = [DatabaseCountry::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao
}

private lateinit var INSTANCE: CountryDatabase

fun getDatabase(context: Context): CountryDatabase {
    synchronized(CountryDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
               CountryDatabase::class.java,
                "countries").build()
        }
    }
    return INSTANCE
}
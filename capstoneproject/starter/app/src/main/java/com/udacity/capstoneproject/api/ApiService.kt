package com.udacity.capstoneproject.api

import androidx.room.Update
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.capstoneproject.Constants.BASE_URL
import com.udacity.capstoneproject.Country
import com.udacity.capstoneproject.GlobalStatistics
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiService {

    interface CovidApiService {
//        getter methods
        @GET("summary")
        suspend fun getAllCountriesAndGlobalStatsData(): String

    }

    object CovidApi {
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val retrofitService: CovidApiService by lazy { retrofit.create(CovidApiService::class.java) }
    }
}
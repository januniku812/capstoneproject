package com.udacity.capstoneproject

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("newDeathsText")
fun TextView.bindNewDeathsText(country: Country) {
    text = String.format(context.getString(R.string.new_deaths_format), country.newDeaths)
}

@BindingAdapter("globalStatisticsNewDeathsText")
fun TextView.bindGlobalStatisticsNewDeathsText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.new_deaths_format), globalStatistics.newDeaths)
}


@BindingAdapter("totalDeathsText")
fun TextView.bindTotalDeathsText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.total_deaths_format), globalStatistics.totalDeaths)
}

@BindingAdapter("newlyRecoveredText")
fun TextView.bindNewlyRecoveredText(country: Country) {
    text = String.format(context.getString(R.string.newly_recovered_format), country.newRecovered)
}

@BindingAdapter("globalStatisticsNewlyRecoveredText")
fun TextView.bindGlobalStatisticsNewlyRecoveredText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.new_deaths_format), globalStatistics.newRecovered)
}


@BindingAdapter("totalRecoveredText")
fun TextView.bindTotalRecoveredText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.total_recovered_format), globalStatistics.totalRecovered)
}

@BindingAdapter("newlyConfirmedText")
fun TextView.bindnewlyConfirmedText(country: Country) {
    text = String.format(context.getString(R.string.newly_confirmed_format), country.newConfirmed)
}

@BindingAdapter("globalStatisticsNewlyConfirmedText")
fun TextView.bindglobalStatisticsNewlyConfirmedText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.newly_confirmed_format), globalStatistics.newConfirmed)
}



@BindingAdapter("totalConfirmedText")
fun TextView.bindTotalConfirmedText(globalStatistics: GlobalStatistics) {
    text = String.format(context.getString(R.string.total_confirmed_format), globalStatistics.totalConfirmed)
}
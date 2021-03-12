package com.udacity.capstoneproject.main

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.capstoneproject.Country
import com.udacity.capstoneproject.NetworkStatus
import com.udacity.capstoneproject.databinding.FragmentMainBinding

@RequiresApi(Build.VERSION_CODES.N)
class MainFragment : Fragment() {

     private val viewModel: MainViewModel by lazy {
         ViewModelProvider(this, MainViewModel.Factory(requireActivity().application)).get(MainViewModel::class.java)
     }

    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
//      setting lifecycle owner
        binding.lifecycleOwner = this
//      setting viewModel
        binding.viewModel = viewModel

//      adapter for the recyclerview to display our asteroid data
        val mainAdapter = MainCountryListAdapter(::countryClick)
//        setting the adapter for the recycler view in which the asteroids will be displayed
        binding.countryRecycler.adapter = mainAdapter
        viewModel.countryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mainAdapter.submitList(it)
            }
        })


//      status of the asteroid list data
        viewModel.status.observe(viewLifecycleOwner, Observer {
            it.let {
                when(it){
                    NetworkStatus.LOADING -> binding.statusLoadingWheel.visibility = View.VISIBLE
                    NetworkStatus.DONE -> binding.statusLoadingWheel.visibility = View.INVISIBLE
                    NetworkStatus.ERROR -> binding.errorGettingDataTextView.visibility = View.VISIBLE
                }
            }
        })

//      status of the picture of the day
        viewModel.globalStatisticsStatus.observe(viewLifecycleOwner, Observer {
            it.let {
                when(it){
                    NetworkStatus.LOADING -> binding.statusLoadingWheel.visibility = View.VISIBLE
                    NetworkStatus.DONE -> binding.statusLoadingWheel.visibility = View.INVISIBLE
                    NetworkStatus.ERROR -> binding.couldNotRetrieveGlobalStatisticsTextView.visibility = View.VISIBLE
                }
            }
        })

        binding.executePendingBindings()

//      allowing the user to have the menu only in the MainFragment and let them choose out
//      of the three choices in the main_overflow_menu
        setHasOptionsMenu(true)

        return binding.root
    }

    fun countryClick(country: Country) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(country))
    }

}

package com.udacity.capstoneproject.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.capstoneproject.R
import com.udacity.capstoneproject.databinding.FragmentHelpBinding

class HelpFragment: Fragment() {

    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false )



        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
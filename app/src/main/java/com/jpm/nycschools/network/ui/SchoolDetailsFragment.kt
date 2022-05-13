package com.jpm.nycschools.network.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jpm.nycschools.NYCSchoolViewModel
import com.jpm.nycschools.R
import kotlinx.android.synthetic.main.fragmnet_details.*

class SchoolDetailsFragment : Fragment() {
    lateinit var viewModel: NYCSchoolViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragmnet_details, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(NYCSchoolViewModel::class.java)
        viewModel.resetGoToSchool()
        setObserver()
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setObserver() {
        viewModel.lEachSchoolInfo.observe(viewLifecycleOwner) {
            it?.let {
                schoolAddress.text = "Address: "+it.location
                schoolName.text = "Name: "+it.school_name
                admissionspriority11.text = it.admissionspriority11
                admissionspriority21.text = it.admissionspriority21
                admissionspriority31.text = it.admissionspriority31

            }
        }

    }
}
package com.jpm.nycschools.network.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpm.nycschools.NYCSchoolViewModel
import com.jpm.nycschools.R
import kotlinx.android.synthetic.main.fragment_school_list.*

class SchoolListFragment : Fragment() {
    lateinit var viewModel: NYCSchoolViewModel
    lateinit var adapter: SchoolAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_school_list, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(NYCSchoolViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = SchoolAdapter{
            viewModel.goToSchoolDetailFragmennt(it)
        }
        setObserver()
        recyclerView.adapter = adapter
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setObserver() {
        viewModel.lSchoolList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateSchoolInfo(it)
            }
        }

    }
}
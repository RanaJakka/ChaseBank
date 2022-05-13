package com.jpm.nycschools.network.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jpm.nycschools.NYCSchoolInfo
import com.jpm.nycschools.R

class SchoolAdapter(val onClick: (NYCSchoolInfo)->Unit) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    val schoolInfoList = mutableListOf<NYCSchoolInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.school_item,parent,false))
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bindSchoolData(schoolInfoList[position])

    }

    override fun getItemCount() = schoolInfoList.size

    fun updateSchoolInfo(list: List<NYCSchoolInfo>){
        schoolInfoList.clear()
        schoolInfoList.addAll(list)
        this.notifyDataSetChanged()
    }

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolName: TextView
        val schoolAddress: TextView
        init {
            schoolName = itemView.findViewById(R.id.schoolName)
            schoolAddress = itemView.findViewById(R.id.schoolAddress)
            itemView.setOnClickListener { onClick(schoolInfoList[adapterPosition]) }
        }
        fun bindSchoolData(schoolsInfo: NYCSchoolInfo){
            schoolName.text = schoolsInfo.school_name
            schoolAddress.text = "Address: "+schoolsInfo.location+"\n Phone: "+schoolsInfo.phone_number
        }

    }
}


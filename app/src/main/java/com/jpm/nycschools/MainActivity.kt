package com.jpm.nycschools

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jpm.nycschools.network.ui.SchoolDetailsFragment
import com.jpm.nycschools.network.ui.SchoolListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  lateinit var viewModel : NYCSchoolViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(NYCSchoolViewModel::class.java)
        setupAllObservers()
        viewModel.makeSchoolsApiCall()

    }
    
    fun setupAllObservers(){
        viewModel.lProgressIndicator.observe(this){
            it?.let {
                if(it)
                    processingIndicator.visibility = View.VISIBLE
                else {
                    processingIndicator.visibility = View.GONE
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.containerFrame, SchoolListFragment(), "SchoolListFragment")
                        .commit()
                    }
                }
            }

        viewModel.lgoTOSchoolDetailFragmnet.observe(this){
            it?.let {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerFrame, SchoolDetailsFragment(), "SchoolDetailFragment")
                    .addToBackStack(null)
                    .commit()

            }
        }
        }
}
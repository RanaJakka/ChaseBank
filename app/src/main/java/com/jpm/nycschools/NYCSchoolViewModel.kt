package com.jpm.nycschools

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jpm.nycschools.network.GetSchoolsInfo
import com.jpm.nycschools.network.RetrofitHelper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

data class NYCSchoolInfo(
    val school_name: String,
    val location: String,
    val phone_number: String?,
    var admissionspriority11: String = "",
    var admissionspriority21: String = "",
    var admissionspriority31: String = "",
)

class NYCSchoolViewModel(application: Application) : AndroidViewModel(application) {
    private val nycSchoolInfoList = mutableListOf<NYCSchoolInfo>()
    private val mProgressIndicator = MutableLiveData<Boolean>(false)
    val lProgressIndicator: LiveData<Boolean>
        get() = mProgressIndicator

    private val mSchoolInfoList = MutableLiveData<List<NYCSchoolInfo>>()
    val lSchoolList: LiveData<List<NYCSchoolInfo>>
        get() = mSchoolInfoList


    private val mSchoolInfo = MutableLiveData<NYCSchoolInfo>()
    val lEachSchoolInfo: LiveData<NYCSchoolInfo>
        get() = mSchoolInfo

    private val errorMessage = MutableLiveData<String>()
    val lErrorMessage: LiveData<String>
        get() = errorMessage

    // general error case handling
    val coroutineExceptions = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorMessage.value = throwable.message
        mProgressIndicator.value = false
    }

    private val mGoToSchoolDetail = MutableLiveData<Boolean>(null)
    val lgoTOSchoolDetailFragmnet: LiveData<Boolean>
    get() = mGoToSchoolDetail


    fun makeSchoolsApiCall() {
        viewModelScope.launch(coroutineExceptions) {
            mProgressIndicator.value = true
            val results = RetrofitHelper.getInstance().create(GetSchoolsInfo::class.java).getNYSSchool()
            if(results.isSuccessful)
                mSchoolInfoList.value =results.body()
            else
                errorMessage.value = results.errorBody().toString()
            mProgressIndicator.value = false
        }
    }

    fun goToSchoolDetailFragmennt(it: NYCSchoolInfo) {
        mSchoolInfo.value = it
        mGoToSchoolDetail.value = true
    }
    fun resetGoToSchool(){
        mGoToSchoolDetail.value = null
    }
}
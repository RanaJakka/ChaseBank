package com.jpm.nycschools.network

import com.jpm.nycschools.NYCSchoolInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetSchoolsInfo {
    @Headers("Accept: application/json ; Content-Type: application/json; charset=UTF-8")
    @GET("resource/s3k6-pzi2.json")
    suspend fun getNYSSchool(): Response<List<NYCSchoolInfo>>

}
package com.jpm.nycschools.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    val baseUrl = "https://data.cityofnewyork.us/"
    val logging = HttpLoggingInterceptor()
    var httpClient = OkHttpClient.Builder()
    fun getInstance(): Retrofit {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}
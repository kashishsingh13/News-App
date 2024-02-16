package com.example.newsapiapplication.Network


import com.example.newsapiapplication.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNews {

    companion object {

        private var retrofit: Retrofit? = null

        fun init(): ApiService{

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }

    }
}
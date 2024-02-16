package com.example.newsapiapplication

import android.provider.ContactsContract.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("top-headlines?apiKey=477b5c7103af4a079777c66836f7e7e5")
   fun getData(@Query("country") countryCode:String):Call<com.example.newsapiapplication.Model.Data>

   @GET("top-headlines?apiKey=477b5c7103af4a079777c66836f7e7e5")
   fun getCategoryData(@Query("country") countryCode:String, @Query("category") category:String):Call<com.example.newsapiapplication.Model.Data>




}
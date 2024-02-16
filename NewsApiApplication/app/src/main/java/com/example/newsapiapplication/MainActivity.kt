package com.example.newsapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapplication.Adapter.HoriContentAdapter
import com.example.newsapiapplication.Adapter.NewsAdapter
import com.example.newsapiapplication.Model.Article
import com.example.newsapiapplication.Model.Content
import com.example.newsapiapplication.Model.Country
import com.example.newsapiapplication.Model.Data
import com.example.newsapiapplication.Network.ApiNews
import com.example.newsapiapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var contentList = mutableListOf<Content>()
    private var newsList = mutableListOf<Article>()
    private var countyList = mutableListOf<Country>()
    private lateinit var contentAdapter: HoriContentAdapter
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter(this, newsList)
        binding.news.layoutManager = LinearLayoutManager(this)
        binding.news.adapter = newsAdapter

        getNews()
//        prepareData()
//       contentAdapter = HoriContentAdapter(this,contentList)
//        binding.viewpager2.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
//        binding.viewpager2.adapter = contentAdapter

    }


    private fun prepareData() {
        contentList.add(Content(1, category = "Bussiness"))
        contentList.add(Content(2, category = "Entertainment"))
        contentList.add(Content(3, category = "General"))
        contentList.add(Content(4, category = "Health"))
        contentList.add(Content(5, category = "Science"))
        contentList.add(Content(6, category = "Sports"))
        contentList.add(Content(7, category = "Technology"))


    }

    private fun CountryData() {
        countyList.add(Country(1, "India", "in"))
        countyList.add(Country(2, "Australia", "au"))
        countyList.add(Country(3, "France", "fr"))
        countyList.add(Country(4, "China", "cn"))
        countyList.add(Country(5, "HongKong", "hk"))
        countyList.add(Country(6, "SaudiArabia", "sa"))
        countyList.add(Country(7, "Switzerland", "ch"))
        countyList.add(Country(8, "Philippines", "ph"))
        countyList.add(Country(9, "Russia", "ru"))
        countyList.add(Country(10, "Germany", "de"))


    }





    private fun getNews() {
        CountryData()

        var countryAdapter = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            countyList.map { it.countryname }
        )
        binding.autoCountry.setAdapter(countryAdapter)

        binding.autoCountry.onItemClickListener = object :
            AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountryCode = countyList[position].countycode
                // Now you can use the selectedCountryCode as needed

                loadCountry(selectedCountryCode)
                prepareData()
                contentAdapter = HoriContentAdapter(applicationContext,contentList)
                binding.viewpager2.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.HORIZONTAL,false)
                binding.viewpager2.adapter = contentAdapter
            }

        }

    }

    private fun loadCountry(countryCode: String) {
       ApiNews.init().getData(countryCode).enqueue(object :Callback<Data>{
           override fun onResponse(call: Call<Data>, response: Response<Data>) {
               if (response.isSuccessful){
                   var newsCountry = response.body()?.articles
                   newsCountry?.let {
                       newsAdapter.newsList = it
                       newsAdapter.notifyDataSetChanged()
                       contentAdapter.itemCardClickListener={
                           position, category ->
                           getCategoryData(countryCode,position,category)
                       }
                   }
               }
           }

           override fun onFailure(call: Call<Data>, t: Throwable) {
               TODO("Not yet implemented")
           }

       })
    }

    private fun getCategoryData(countryCode: String,position:Int, category:Content){
        ApiNews.init().getCategoryData(countryCode,category.category).enqueue(object :Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
              if(response.isSuccessful){
                  var news = response.body()?.articles
                  news?.let {
                      newsAdapter.newsList=it
                      newsAdapter.notifyDataSetChanged()
                  }
              }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
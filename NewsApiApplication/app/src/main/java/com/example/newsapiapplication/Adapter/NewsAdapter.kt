package com.example.newsapiapplication.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapplication.Model.Article
import com.example.newsapiapplication.Model.Content
import com.example.newsapiapplication.R
import com.example.newsapiapplication.databinding.NewsBinding
import com.squareup.picasso.Picasso

class NewsAdapter (var  context: Context, var newsList: MutableList<Article>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: NewsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.url.setOnClickListener {
                val url = binding.url.text.toString()
                openUrlInBrowser(url)
            }
        }

        private fun openUrlInBrowser(url: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            binding.root.context.startActivity(browserIntent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = NewsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var news = newsList[position]

        Picasso.get().load(news.image).into(holder.binding.image)

        holder.binding.title.text = news.title
        holder.binding.desc.text = news.description
        holder.binding.url.text = news.url




        }



}

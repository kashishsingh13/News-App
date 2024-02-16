package com.example.newsapiapplication.Model

data class Data(
    var status:String,
    var totalResults:Int,
    var articles:MutableList<Article>
)

package com.example.newsapiapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapplication.Model.Content
import com.example.newsapiapplication.R

class HoriContentAdapter(var  context: Context, var contentList: MutableList<Content>): RecyclerView.Adapter<HoriContentAdapter.MyViewHolder>() {
    var  itemCardClickListener: ((position: Int, category:Content) -> Unit)? = null
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var  text: TextView

        init {
            text = itemView.findViewById(R.id.content)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.content, parent, false))

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageResource = contentList[position]
        holder.text.text=imageResource.category
       holder.text.setOnClickListener {
           itemCardClickListener?.invoke(position,imageResource)
       }
    }
    override fun getItemCount(): Int {
        return contentList.size
    }

}
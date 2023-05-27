package com.example.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Result>?): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

   inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList?.get(position)

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList?.get(position)?.posterPath).into(holder.imageView)




    }

    override fun getItemCount(): Int {
        return mList!!.size
    }
}
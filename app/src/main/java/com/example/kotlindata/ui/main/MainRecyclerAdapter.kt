package com.example.kotlindata.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindata.R
import com.example.kotlindata.data.Monster

class MainRecyclerAdapter(private val context: Context, private val monster: List<Monster>) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.monster_grid_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount() = monster.size

    override fun onBindViewHolder(holder: MainRecyclerAdapter.MyViewHolder, position: Int) {

        val monster = monster[position]
        with(holder) {
            nameText?.let {
                it.text = monster.name
                it.contentDescription = monster.name
            }
            ratingBar?.rating = monster.scariness.toFloat()
            Glide.with(context)
                .load(monster.imageUrl)
                .into(monsterImg)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val monsterImg = itemView.findViewById<ImageView>(R.id.monsterImage)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    }
}
package com.example.marsapp.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsapp.Model.MarsApiClass
import com.example.myapp58.R


class AdapterMars(private val datos: List<MarsApiClass>) : RecyclerView.Adapter<AdapterMars._ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): _ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return _ViewHolder(view)
    }


    override fun onBindViewHolder(holder: _ViewHolder, position: Int) {
        val item = datos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return datos.size
    }


    inner class _ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageItemView: ImageView =itemView.findViewById(R.id.imageView)
        fun bind(item: MarsApiClass) {

            Glide.with(itemView)
                .load(item.imgSrc)
                .into(imageItemView)
        }
    }
}
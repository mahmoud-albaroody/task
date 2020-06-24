package com.example.mahmoud_fetouh_task.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mahmoud_fetouh_task.R
import com.example.mahmoud_fetouh_task.model.Data
import kotlinx.android.synthetic.main.item_recycle.view.*

import kotlin.collections.ArrayList

class ListAdapter(
       val mActivity: Activity,
         val data: ArrayList<Data>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_recycle,
                            parent,
                            false
                    )
            )


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setData(holder)
    }

    private fun setData(holder: ViewHolder) {
        Glide.with(holder.carImage).load(data[0].imageUrl.trim()).into(holder.carImage)
        holder.constraction_year.text =  mActivity.getString(R.string.ConstructionYear) +data[0].constructionYear
        if (data[0].isUsed) {
            holder.is_used.text = mActivity.getString(R.string.isUsed)  +"yes"
        } else {
            holder.is_used.text =mActivity.getString(R.string.isUsed) + "no"
        }
        holder.brand.text = mActivity.getString(R.string.brand) + data[0].brand
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carImage = view.car_image
        val constraction_year = view.constraction_year
        val is_used = view.is_used
        val brand = view.brand

    }
}
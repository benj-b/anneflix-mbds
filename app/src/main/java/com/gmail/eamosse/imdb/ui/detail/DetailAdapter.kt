package com.gmail.eamosse.imdb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Detail
import com.gmail.eamosse.imdb.databinding.FragmentDetailBinding

class DetailAdapter(
    private val detail: Detail
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FragmentDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: Detail) {
            binding.detail = detail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(FragmentDetailBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return 1;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detail)
    }
}
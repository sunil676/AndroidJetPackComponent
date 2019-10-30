package com.sunil.androidjetpackcomponent.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.model.Trailer
import kotlinx.android.synthetic.main.item_trailer.view.*

class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    private var trailerList: List<Trailer>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrailersAdapter.TrailersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trailer, parent, false)
        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (trailerList != null) trailerList!!.size else 0
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        val trailer = trailerList!!.get(position);
        trailer?.let {
            holder.setData(trailer)
        }
    }

    fun submitList(trailers: List<Trailer>) {
        trailerList = trailers
        notifyDataSetChanged()
    }

    class TrailersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(trailer: Trailer) {
            itemView.trailer_name.text = trailer.title

            Glide.with(itemView.context)
                .load("https://img.youtube.com/vi/" + trailer.key + "/hqdefault.jpg")
                .dontAnimate()
                .into(itemView.image_trailer)
        }

    }

}
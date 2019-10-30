package com.sunil.androidjetpackcomponent.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.model.Cast
import com.sunil.androidjetpackcomponent.model.Movie
import kotlinx.android.synthetic.main.item_cast.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var castList: List<Cast>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (castList != null) castList!!.size else 0
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = castList!!.get(position);
        cast?.let {
            holder.setData(cast)
        }
    }

    fun submitList(casts: List<Cast>) {
        castList = casts
        notifyDataSetChanged()
    }

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(cast: Cast) {
            itemView.text_cast_name.text = cast.actorName

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w185"+cast.profileImagePath)
                .dontAnimate()
                .into(itemView.image_cast_profile)
        }

    }

}
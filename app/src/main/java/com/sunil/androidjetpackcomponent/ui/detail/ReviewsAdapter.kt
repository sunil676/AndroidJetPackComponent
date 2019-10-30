package com.sunil.androidjetpackcomponent.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.Glide
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.model.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    private var reviewList: List<Review>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsAdapter.ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (reviewList != null) reviewList!!.size else 0
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewList!!.get(position);
        review?.let {
            holder.setData(review)
        }
    }

    fun submitList(review: List<Review>) {
        reviewList = review
        notifyDataSetChanged()
    }

    class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(review: Review) {
            itemView.text_author.text = review.author

            itemView.text_content.text = review.content

            val generator = ColorGenerator.MATERIAL
            val color = generator.getRandomColor()
            val drawable = TextDrawable.builder()
                .buildRound(review.author!!.substring(0, 1).toUpperCase(), color)

            Glide.with(itemView.context)
                .load(drawable)
                .dontAnimate()
                .into(itemView.image_author)
        }

    }

}
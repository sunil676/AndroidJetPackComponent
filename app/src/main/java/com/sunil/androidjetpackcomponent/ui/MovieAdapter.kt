package com.sunil.androidjetpackcomponent.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let {
            holder.setData(repo)
        }
    }


    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setData(movie: Movie) {
            view.text_title.text = movie.title

            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
                .centerCrop()
                .into(view.image_movie_poster)

        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldMovie: Movie, newMovie: Movie): Boolean =
                oldMovie.id == newMovie.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie): Boolean =
                oldMovie == newMovie
        }
    }
}
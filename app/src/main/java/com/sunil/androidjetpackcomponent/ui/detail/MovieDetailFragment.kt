package com.sunil.androidjetpackcomponent.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.model.Movie
import com.sunil.androidjetpackcomponent.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.partial_details_info.view.*

class MovieDetailFragment : Fragment() {

    var movie: Movie? = null
    private lateinit var rootView: View
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel.getMovieDetail(args.movieId.toString().toLongOrNull()!!)
            .observe(this, Observer {
                it.let {
                    movie = it
                    updateView(it)
                }
            })

        return rootView
    }

    fun updateView(movie: Movie) {

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
            .dontAnimate()
            .into(rootView.image_poster)

        rootView.text_title.text = movie.title

        rootView.text_release_date.text = movie.releaseDate

        rootView.text_vote.text = movie.voteAverage.toString()

        rootView.text_language.text = movie.originalLanguage

        rootView.text_overview.text = movie.overview

        setupTrailersAdapter(movie)
        setupCastAdapter(movie)
        setupReviewsAdapter(movie)
    }

    private fun setupTrailersAdapter(movie: Movie) {
        rootView.list_trailers.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val trailerAdapter = TrailersAdapter()
        rootView.list_trailers.adapter = trailerAdapter
        trailerAdapter.submitList(movie.trailersResponse!!.trailers!!)
        ViewCompat.setNestedScrollingEnabled(rootView.list_trailers, false)
    }

    private fun setupCastAdapter(movie: Movie) {
        rootView.list_cast.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val castAdapter = CastAdapter()
        rootView.list_cast.adapter = castAdapter
        castAdapter.submitList(movie.creditsResponse!!.cast!!)
        ViewCompat.setNestedScrollingEnabled(rootView.list_cast, false)
    }

    private fun setupReviewsAdapter(movie: Movie) {
        rootView.list_reviews.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val reviewsAdapter = ReviewsAdapter()
        rootView.list_reviews.adapter = reviewsAdapter
        reviewsAdapter.submitList(movie.reviewsResponse!!.reviews!!)
        ViewCompat.setNestedScrollingEnabled(rootView.list_reviews, false)
    }


}
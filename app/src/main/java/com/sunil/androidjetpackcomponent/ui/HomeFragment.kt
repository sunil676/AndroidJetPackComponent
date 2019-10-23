package com.sunil.androidjetpackcomponent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunil.androidjetpackcomponent.R
import com.sunil.androidjetpackcomponent.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val adapter = MovieAdapter()
        rootView.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        val itemViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        itemViewModel.moviePageList.observe(this, Observer {
            adapter.submitList(it)
        })

        rootView.recyclerView.adapter = adapter
        //rootView.recyclerView.addItemDecoration(RecyclerView.ItemDecoration(requireActivity(), 3))

        return rootView
    }

}
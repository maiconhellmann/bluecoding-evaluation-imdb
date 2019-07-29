package com.hellmann.bluecoding.feature.movie.theaternow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.FragmentMoviesNowBinding
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.util.extensions.toast
import com.hellmann.bluecoding.util.extensions.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 26/07/2019
 * 
 * (c) 2019 
 */class TheaterNowFragment: Fragment() {

    private lateinit var binding: FragmentMoviesNowBinding
    private val viewModel: TheaterNowViewModel by viewModel()
    private val theaterNowAdapter: TheaterNowAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies_now, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView()
        setupViewModel()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMoviesInTheater()
        }
    }

    private fun setupViewModel() {
        viewModel.getMoviesInTheater()

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ViewState.Success -> {
                    theaterNowAdapter.movies = state.data
                    setVisibilities(showList = true)
                }
                is ViewState.Failed -> {
                    showError(state.throwable)
                    setVisibilities(showError = true)
                }
                is ViewState.Loading -> {
                    setVisibilities(showProgressBar = true)
                }
            }
        })
    }

    /**
     * Setup the recycleView layout manager and set the adapter.
     */
    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.movie_grid_columns))
        adapter = theaterNowAdapter
    }

    /**
     * Show a really basic error message to the user
     */
    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(TheaterNowFragment::class.java.simpleName, "Error", throwable)
    }

    /**
     * Centralized method to set the component visibilities.
     */
    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showList: Boolean = false,
        showError: Boolean = false,
        isRefreshing: Boolean = false
    ) {
        binding.progressBar.visible(showProgressBar)
        binding.recyclerView.visible(showList)
        binding.btnTryAgain.visible(showError)
        binding.swipeRefresh.isRefreshing = isRefreshing
    }
}
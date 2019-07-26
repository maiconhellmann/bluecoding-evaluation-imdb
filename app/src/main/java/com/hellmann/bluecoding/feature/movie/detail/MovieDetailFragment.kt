package com.hellmann.bluecoding.feature.movie.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.FragmentMovieDetailBinding
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.util.extensions.toast
import com.hellmann.bluecoding.util.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 26/07/2019
 * 
 * (c) 2019 
 */class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupViewModel()
    }

    private fun setupViewModel() {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val id = safeArgs.movieId

        viewModel.getMovieDetail(id)

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ViewState.Loading -> setVisibilities(showProgressBar = true)
                is ViewState.Failed -> {
                    setVisibilities(showError = true)
                    showError(state.throwable)
                }
                is ViewState.Success -> {
                    //TODO update screen
                    setVisibilities(showDetails = true)
                }
            }
        })
    }

    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(MovieDetailFragment::class.java.simpleName, "Error", throwable)
    }

    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showDetails: Boolean = false,
        showError: Boolean = false,
        isRefreshing: Boolean = false
    ) {
        //TODO visibilities
    }
}
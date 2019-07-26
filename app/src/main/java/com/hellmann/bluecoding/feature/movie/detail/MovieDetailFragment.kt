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
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.util.extensions.load
import com.hellmann.bluecoding.util.extensions.toast
import com.hellmann.bluecoding.util.extensions.visible
import kotlinx.android.synthetic.main.fragment_movie_list.progressBar
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

    private val movieId: Int by lazy {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        safeArgs.movieId
    }

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
        addListeners()
    }

    private fun addListeners() {
        binding.btnTryAgain.setOnClickListener { viewModel.onTryAgainRequired(movieId) }
    }

    private fun setupViewModel() {
        viewModel.getMovieDetail(movieId)

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ViewState.Loading -> setVisibilities(showProgressBar = true)
                is ViewState.Failed -> {
                    setVisibilities(showError = true)
                    showError(state.throwable)
                }
                is ViewState.Success -> {
                    setMovieDetails(state.data)
                    setVisibilities(showDetails = true)
                }
            }
        })
    }

    private fun setMovieDetails(movie: Movie) {
        binding.imageViewPoster.load(movie.posterPath)
        binding.textViewTitle.text = movie.title
        binding.textViewYearRelease.text = movie.releaseDate

        if (movie.genres.isNotEmpty()) {
            binding.textViewGenre.text = movie.genres
        }
    }

    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(MovieDetailFragment::class.java.simpleName, "Error", throwable)
    }

    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showDetails: Boolean = false,
        showError: Boolean = false
    ) {
        binding.btnTryAgain.visible(showError)
        binding.layoutMovieDetails.visible(showDetails)
        binding.progressBar.visible(showProgressBar)
    }
}
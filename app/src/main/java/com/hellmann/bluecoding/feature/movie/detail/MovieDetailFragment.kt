package com.hellmann.bluecoding.feature.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.FragmentMovieDetailBinding

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 26/07/2019
 * 
 * (c) 2019 
 */class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

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

        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val id = safeArgs.movieId

        //TODO load movie
    }
}
package com.hellmann.bluecoding.feature.movie.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.databinding.FragmentMovieListBinding
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.util.extensions.toast
import com.hellmann.bluecoding.util.extensions.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(), SearchView.OnQueryTextListener {
    val viewModel: MovieViewModel by viewModel()
    private val movieAdapter: MoviesAdapter by inject()

    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies(true)
        }
    }

    private fun setupViewModel() {
        viewModel.getMovies()

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ViewState.Success -> {
                    movieAdapter.movies = state.data
                    setVisibilities(showList = true)
                }
                is ViewState.Loading -> {
                    setVisibilities(showProgressBar = true)
                }
                is ViewState.Failed -> {
                    setVisibilities(showError = true)
                    showError(state.throwable)
                }
            }
        })
    }

    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e("MainActivity", "Error", throwable)
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.movie_grid_columns))
        adapter = movieAdapter
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.movie_list_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) {
            viewModel.toggleSortingMethod()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Triggered when the user taps o submit search from searchbar
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchMovies(query)
        return true
    }

    /**
     * Triggered whenever the text of the searchbar is changed
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) viewModel.getMovies(false)
        return false
    }


}

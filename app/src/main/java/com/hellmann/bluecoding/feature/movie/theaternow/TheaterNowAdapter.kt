package com.hellmann.bluecoding.feature.movie.theaternow

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.feature.movie.list.MovieListFragmentDirections
import com.hellmann.bluecoding.util.extensions.getString
import com.hellmann.bluecoding.util.extensions.inflate
import com.hellmann.bluecoding.util.extensions.load
import kotlinx.android.synthetic.main.fragment_theater_now_item.view.imageViewPoster
import kotlinx.android.synthetic.main.fragment_theater_now_item.view.textViewRating
import kotlinx.android.synthetic.main.fragment_theater_now_item.view.textViewTittle

class TheaterNowAdapter : RecyclerView.Adapter<TheaterNowAdapter.ViewHolder>() {

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_theater_now_item)) {

        fun bind(movie: Movie) = with(itemView) {
            textViewTittle.text = movie.title
            textViewRating.text =
                getString(R.string.fragment_movie_rating_average, movie.voteAverage)

            imageViewPoster.load(movie.posterPath)

            val directions = MovieListFragmentDirections.actionOpenMovieDetail(movie.id)
            setOnClickListener(Navigation.createNavigateOnClickListener(directions))

            itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}
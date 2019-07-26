package com.hellmann.bluecoding.feature.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellmann.bluecoding.R
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.util.extensions.getString
import com.hellmann.bluecoding.util.extensions.inflate
import com.hellmann.bluecoding.util.extensions.load
import kotlinx.android.synthetic.main.fragment_movie_item.view.imageViewPoster
import kotlinx.android.synthetic.main.fragment_movie_item.view.textViewRating
import kotlinx.android.synthetic.main.fragment_movie_item.view.textViewTittle

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: List<Movie> = listOf()

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.fragment_movie_item)) {

        fun bind(movie: Movie) = with(itemView) {
            textViewTittle.text = movie.title
            textViewRating.text =
                getString(R.string.fragment_movie_rating_average, movie.voteAverage)
            imageViewPoster.load(movie.posterPath)

            //TODO favourite status
            //            if (movie.url != null) {
            //                movie.url?.let {
            //                    val directions = MovieListFragmentDirections.actionOpenWebview(it)
            //                    setOnClickListener(Navigation.createNavigateOnClickListener(directions))
            //                }
            //            } else {
            setOnClickListener(null)
            //            }//TODO open details

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
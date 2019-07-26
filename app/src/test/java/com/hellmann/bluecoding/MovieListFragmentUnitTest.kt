package com.hellmann.bluecoding

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.hellmann.bluecoding.feature.movie.list.MovieListFragment
import com.hellmann.bluecoding.feature.movie.MovieListFragmentDirections
import com.hellmann.bluecoding.feature.movie.list.MoviesAdapter
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will getMovies on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@SmallTest
@RunWith(RobolectricTestRunner::class)
class MovieListFragmentUnitTest {

    private lateinit var mockNavController: NavController
    private lateinit var movieList: FragmentScenario<MovieListFragment>

    @Before
    fun setUp() {
        mockNavController = Mockito.mock(NavController::class.java)
    }

    @Test
    fun `movieListFragment test`() {
        movieList = launchFragmentInContainer<MovieListFragment>().onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)

            //Loading
            it.viewModel.state.postValue(ViewState.Loading)

            assert(it.view?.findViewById<View>(R.id.progressBar)?.isVisible == true)

            //Post a list with one item
            it.viewModel.state.postValue(
                ViewState.Success(
                    listOf(
//                        Movie(
//                            "", "", "http://www.google.com")
                    ))
            )

            //Not loading anymore
            assert(it.view?.findViewById<View>(R.id.progressBar)?.isVisible == false)

            it.view?.findViewById<RecyclerView>(R.id.recyclerView)?.apply {
                val index = 0

                //Url of the current item
                val url = (adapter as MoviesAdapter).movies[index].posterPath
                //TODO test it

                //Click current item
                onView(withId(R.id.recyclerView)).perform(
                    actionOnItemAtPosition<MoviesAdapter.ViewHolder>(
                        index, click()))

                //navigation of the url of the item
                val directions = MovieListFragmentDirections.actionOpenWebview(url)

                //verify destination
                Mockito.verify(mockNavController).navigate(directions)
            }
        }
    }
}

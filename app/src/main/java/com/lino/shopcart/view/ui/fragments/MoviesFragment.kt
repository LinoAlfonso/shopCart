package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.lino.shopcart.R
import com.lino.shopcart.models.Movie
import com.lino.shopcart.view.adapater.MovieAdapter
import com.lino.shopcart.view.adapater.MovieListener
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment(),MovieListener {

    private lateinit var moviesPopularViewModel: MoviesPopularViewModel
    private lateinit var moviesAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesPopularViewModel = ViewModelProviders.of(this).get(MoviesPopularViewModel::class.java)
        moviesPopularViewModel.refreshGetMovies()
        moviesAdapter = MovieAdapter(this)

        rvProducts.apply {
            adapter = moviesAdapter
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        moviesPopularViewModel.listMoviesPopular.observe(
            viewLifecycleOwner, Observer<List<Movie>> { schedule ->
                moviesAdapter.updateData(schedule)
            }
        )
    }

    override fun onMovieClicked(movie: Movie, position: Int) {
        val bundle = bundleOf("idMovie" to movie.id.toString())
        findNavController().navigate(R.id.detailProductFragment,bundle)
    }

}
package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lino.shopcart.R
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.models.Movie
import com.lino.shopcart.repository.MoviesRepository
import com.lino.shopcart.utils.Resource
import com.lino.shopcart.view.adapater.MovieAdapter
import com.lino.shopcart.view.adapater.MovieListener
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import com.lino.shopcart.viewmodel.MoviesPopularViewModelFactory
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
        val repository = MoviesRepository(ShopCartMovieDataBase(view.context))
        val viewModelProviderFactory = MoviesPopularViewModelFactory(repository)
        moviesPopularViewModel = ViewModelProvider(this,viewModelProviderFactory).get(MoviesPopularViewModel::class.java)
        moviesPopularViewModel.refreshGetMovies()
        moviesAdapter = MovieAdapter(this)

        rvProducts.apply {
            adapter = moviesAdapter
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        moviesPopularViewModel.moviesPopular.observe(
            viewLifecycleOwner, Observer {response ->
                when(response){
                    is Resource.Success ->{
                        response.data?.let { moviesResponse ->
                            moviesAdapter.updateData(moviesResponse.listMovies)
                        }
                    }
                    is Resource.Error ->{
                        response.message?.let {
                            println(it)
                        }
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        )
    }

    override fun onMovieClicked(movie: Movie, position: Int) {
        val bundle = bundleOf("idMovie" to movie.id.toString())
        findNavController().navigate(R.id.detailProductFragment,bundle)
    }

}
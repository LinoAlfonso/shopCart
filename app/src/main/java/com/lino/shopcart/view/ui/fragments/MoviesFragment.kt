package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lino.shopcart.MainActivity
import com.lino.shopcart.R
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.databinding.FragmentMoviesBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.repository.MoviesRepository
import com.lino.shopcart.utils.Constants.Companion.ADD_CART
import com.lino.shopcart.utils.Resource
import com.lino.shopcart.view.adapater.MovieAdapter
import com.lino.shopcart.view.adapater.MovieListener
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import com.lino.shopcart.viewmodel.MoviesPopularViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviesFragment : Fragment(),MovieListener {

    private lateinit var moviesPopularViewModel: MoviesPopularViewModel
    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var _moviesFragmentDataBinding: FragmentMoviesBinding
    private val binding get() = _moviesFragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesPopularViewModel = (activity as MainActivity).moviesPopularViewModel
        moviesPopularViewModel.refreshGetMovies()
        moviesAdapter = MovieAdapter(this)
        _moviesFragmentDataBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        hideProgress()
                        response.data?.let { moviesResponse ->
                            moviesAdapter.updateData(moviesResponse.listMovies)
                        }
                    }
                    is Resource.Error ->{
                        hideProgress()
                        lifecycleScope.launchWhenResumed {
                            moviesPopularViewModel.getAllMoviesSave()
                                .observe(viewLifecycleOwner, Observer { listMovies ->
                                    moviesAdapter.updateData(listMovies)
                                })
                            withContext(Dispatchers.IO){
                                response.message?.let {
                                    Snackbar.make(binding.root, it,Snackbar.LENGTH_SHORT).apply {
                                        show()
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Loading -> {
                        showProgress()
                    }
                }
            }
        )
    }

    private fun hideProgress(){
        binding.rlProducts.visibility = View.GONE
    }

    private fun showProgress(){
        binding.rlProducts.visibility = View.VISIBLE
    }

    override fun onMovieClicked(movie: Movie, typeClick: Int) {
        val bundle = Bundle().apply {
            putSerializable("movie",movie)
        }
       if(typeClick == ADD_CART){
           lifecycleScope.launch {
               moviesPopularViewModel.updateQuantityProduct(movie.id,movie.countCart+1,true)
               withContext(Dispatchers.IO){
                   Snackbar.make(binding.root,getString(R.string.addProductCart),Snackbar.LENGTH_SHORT).apply {
                       show()
                   }
               }
           }

       }else{
           findNavController().navigate(R.id.detailProductFragment,bundle)
       }

    }

}
package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lino.shopcart.R
import com.lino.shopcart.models.Movie
import com.lino.shopcart.view.adapater.ProductAdapter
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private lateinit var moviesPopularViewModel: MoviesPopularViewModel
    private lateinit var productsAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesPopularViewModel = ViewModelProviders.of(this).get(MoviesPopularViewModel::class.java)
        moviesPopularViewModel.refreshGetMovies()
        productsAdapter = ProductAdapter()

        rvProducts.apply {
            adapter = productsAdapter
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        moviesPopularViewModel.listMoviesPopular.observe(
            viewLifecycleOwner, Observer<List<Movie>> { schedule ->
                productsAdapter.updateData(schedule)
            }
        )
    }

}
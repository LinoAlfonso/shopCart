package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.lino.shopcart.R
import com.lino.shopcart.databinding.FragmentDetailMovieBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.utils.bindImageUrl
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    val moviesPopularViewModel:MoviesPopularViewModel by viewModels()
    private lateinit var _detailMovieBinding: FragmentDetailMovieBinding
    private val binding get() = _detailMovieBinding
    val args : DetailMovieFragmentArgs by navArgs()
    private lateinit var movie : Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _detailMovieBinding =   FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = args.movie
        setupMovie()
        updateQuantity()

    }

    private fun setupMovie(){
        movie.let {detailMovie->
            binding.detailMovie = detailMovie
            binding.imgMovieDetail.bindImageUrl(
                url = "https://image.tmdb.org/t/p/w500/"+detailMovie.posterPath,
                placeholder = R.drawable.ic_broken_image,
                errorPlaceholder = R.drawable.ic_broken_image
            )
        }

    }

    private fun updateQuantity(){
        binding.btnAddQuantity.setOnClickListener {
            binding.detailMovie!!.countCart++
            binding.invalidateAll()
        }
        binding.btnRemoveQuantity.setOnClickListener {
            if(binding.detailMovie!!.countCart>0){
                binding.detailMovie!!.countCart--
                binding.invalidateAll()
            }
        }
        binding.btnAddCart.setOnClickListener {
            if(binding.detailMovie!!.countCart>=1){
                lifecycleScope.launch {
                    moviesPopularViewModel.updateQuantityProduct(binding.detailMovie!!.id,binding.detailMovie!!.countCart,true)
                    withContext(Dispatchers.IO){
                        Snackbar.make(binding.root,getString(R.string.addProductCart), Snackbar.LENGTH_SHORT).apply {
                            show()
                        }
                    }
                }
            }
        }
    }

}
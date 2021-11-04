package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.lino.shopcart.R
import com.lino.shopcart.databinding.FragmentDetailMovieBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.utils.bindImageUrl
import com.lino.shopcart.viewmodel.DetailMovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class DetailMovieFragment : Fragment() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
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
        //val id_movie = arguments?.getString("idMovie")
        //detailMovieViewModel =  ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
        //id_movie?.let { detailMovieViewModel.getDetailMovie(it) }
        //observableViewModel(this)
    }

    private fun observableViewModel(owner:LifecycleOwner){
        detailMovieViewModel.isLoading.observe(owner, Observer {loading->

        })
        detailMovieViewModel.detailMovie.observe(owner, Observer {movieDetail->
       /*     if(movieDetail!=null){
                binding.detailMovie = movieDetail
                binding.imgMovieDetail.bindImageUrl(
                    url = "https://image.tmdb.org/t/p/w500/"+movieDetail.posterPath,
                    placeholder = R.drawable.ic_broken_image,
                    errorPlaceholder = R.drawable.ic_broken_image
                )
            }*/
        })
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

    private fun updateQuantity(movie: Movie){
        binding.btnAddCart.setOnClickListener {
            movie.countCart = movie.countCart?.plus(1)
        }
    }

}
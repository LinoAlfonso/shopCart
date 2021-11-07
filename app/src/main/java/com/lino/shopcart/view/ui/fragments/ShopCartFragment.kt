package com.lino.shopcart.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.lino.shopcart.R
import com.lino.shopcart.databinding.FragmentShopCartBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.utils.Constants.Companion.ADD_CART
import com.lino.shopcart.utils.Constants.Companion.DELETE_ITEM
import com.lino.shopcart.utils.Constants.Companion.REMOVE_CART
import com.lino.shopcart.view.adapater.ShopCartAdapter
import com.lino.shopcart.view.adapater.ShopCartListener
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shop_cart.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ShopCartFragment : Fragment(),ShopCartListener {

    val moviesPopularViewModel:MoviesPopularViewModel by viewModels()
    private lateinit var shopCartAdapter: ShopCartAdapter
    private lateinit var _fragmentShopCartBinding: FragmentShopCartBinding
    private val binding get() = _fragmentShopCartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _fragmentShopCartBinding = FragmentShopCartBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(requireActivity().findViewById(R.id.toolbarMain) as Toolbar).findViewById<ImageButton>(R.id.btnShopCart).visibility = View.VISIBLE
        shopCartAdapter = ShopCartAdapter(this)
        rvMoviesCart.apply {
            adapter = shopCartAdapter
        }



        lifecycleScope.launchWhenResumed {
            moviesPopularViewModel.getMoviesInCartSave()
                .observe(viewLifecycleOwner, Observer { listCart ->
                    shopCartAdapter.updateData(listCart)
                })
        }

    }

    override fun clickListenerMovie(movie: Movie, typeClick: Int) {
        when(typeClick){
            ADD_CART ->{
                lifecycleScope.launch {
                    moviesPopularViewModel.updateQuantityProduct(movie.id,movie.countCart+1,true)
                }
            }
            REMOVE_CART->{
                if(movie.countCart>1) {
                    lifecycleScope.launch {
                        moviesPopularViewModel.updateQuantityProduct(
                            movie.id,
                            movie.countCart-1,
                            true
                        )
                    }
                }
            }
            DELETE_ITEM ->{
                lifecycleScope.launch {
                    moviesPopularViewModel.updateQuantityProduct(movie.id,0,false)
                    withContext(Dispatchers.IO){
                        Snackbar.make(binding.root,getString(R.string.deleteCart), Snackbar.LENGTH_SHORT).apply {
                            show()
                        }
                    }
                }
            }
        }
    }

}
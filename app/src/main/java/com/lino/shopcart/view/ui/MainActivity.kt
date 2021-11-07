package com.lino.shopcart.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lino.shopcart.R
import com.lino.shopcart.databinding.ActivityMainBinding
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val moviesPopularViewModel:MoviesPopularViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val repository = MoviesRepository(ShopCartMovieDataBase(this))
       // val viewModelProviderFactory = MoviesPopularViewModelFactory(application,repository)
       // moviesPopularViewModel = ViewModelProvider(this,viewModelProviderFactory).get(MoviesPopularViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragContent)
        binding.toolbarMain.setupWithNavController(navController)

        binding.btnShopCart.setOnClickListener {
            navController.navigate(R.id.shopCartFragment)
        }
    }


}
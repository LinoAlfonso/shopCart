package com.lino.shopcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.databinding.ActivityMainBinding
import com.lino.shopcart.repository.MoviesRepository
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import com.lino.shopcart.viewmodel.MoviesPopularViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var moviesPopularViewModel:MoviesPopularViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MoviesRepository(ShopCartMovieDataBase(this))
        val viewModelProviderFactory = MoviesPopularViewModelFactory(repository)
        moviesPopularViewModel = ViewModelProvider(this,viewModelProviderFactory).get(MoviesPopularViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragContent)
        binding.toolbarMain.setupWithNavController(navController)

        binding.btnShopCart.setOnClickListener {
            navController.navigate(R.id.shopCartFragment)
        }
    }


}
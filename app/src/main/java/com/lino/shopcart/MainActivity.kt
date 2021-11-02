package com.lino.shopcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lino.shopcart.databinding.ActivityMainBinding
import com.lino.shopcart.viewmodel.MoviesPopularViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var moviesPopularViewModel:MoviesPopularViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragContent)
        binding.toolbarMain.setupWithNavController(navController)
    }
}
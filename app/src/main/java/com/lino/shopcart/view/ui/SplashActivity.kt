package com.lino.shopcart.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.lino.shopcart.MainActivity
import com.lino.shopcart.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intentHome = Intent(this, MainActivity::class.java)
        startActivity(intentHome)
        finish()
    }


}
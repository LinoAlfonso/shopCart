package com.lino.shopcart.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.lino.shopcart.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer("SettingUp", false).schedule(2000) {
            openApp()
        }
    }


    fun openApp() {
        val intentHome = Intent(this, MainActivity::class.java)
        startActivity(intentHome)
        finish()
    }

}
package com.example.proyecto3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class splash : AppCompatActivity()
{

    val splash_time:Long = 3000
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
    }
}
package com.example.proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_screen.*

class splash : AppCompatActivity()
{
    lateinit var idvrv: Handler
    lateinit var handler2: Handler
    lateinit var fadeout: Animation
    override fun onCreate(savedInstanceState: Bundle?)
    {
        fadeout= AnimationUtils.loadAnimation(this,R.anim.fade_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        iv_logo.setImageResource(R.drawable.kirpog)
        tv_presentation.text= "Desarrollado por:"
        iv_logo.startAnimation(fadeout)
        tv_presentation.startAnimation(fadeout)

        idvrv = Handler()
        idvrv.postDelayed({
            iv_logo.setImageResource(R.drawable.wind)
            tv_presentation.text = "Para ser presentado en:"
        },3000)

        handler2 = Handler()
        handler2.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000)
    }
}
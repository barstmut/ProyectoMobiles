package com.example.proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    lateinit var blink: Animation
    lateinit var fadeout: Animation

    override fun onCreate(savedInstanceState: Bundle?) 
    {
        blink= AnimationUtils.loadAnimation(this,R.anim.blink)
        super.onCreate(savedInstanceState)

        val saveData = SaveData(applicationContext)
        saveData.setAlarm()

        setContentView(R.layout.activity_main)
        tv_push.startAnimation(blink)
    }

    fun change(view: View)
    {
        fadeout= AnimationUtils.loadAnimation(this,R.anim.fade_out)
        tv_push.startAnimation(fadeout)
        val intent: Intent = Intent(this, category::class.java)
        startActivity(intent)
    }


}
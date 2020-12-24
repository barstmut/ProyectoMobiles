package com.example.proyecto3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.full_service.*

class fullService : AppCompatActivity()
{
    var fav = false
    var url:String?=null
    var phone:String?=null
    var name:String?=null
    var category:String?=null
    var descuento = (10..90).shuffled().first()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_service)

        val bundle = intent.extras
        name =bundle!!.getString("nombre")
        category = bundle!!.getString("categoria")
        url = bundle!!.getString("url")
        phone = bundle!!.getString("phone")

        tv_fullname.text = name
        iv_fullitem.setImageResource(bundle!!.getInt("img"))
    }

    fun go_bowser(view: View)
    {
        val uri:Uri= Uri.parse(url)
        val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun llamar(view: View)
    {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:<$phone>")
        startActivity(intent)
    }

    fun wsp(view: View)
    {
        val sendIntent: Intent = Intent().apply {
            val ye = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Descuentos en $category :\n" +
                    "Obten un $descuento de descuento en $name \n" +
                    "Para más informacion contactate en: \n" +
                    "Telefono: $phone \n " +
                    "Sitio Web: $url")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "App de Descuentos")
        startActivity(shareIntent)
    }

}
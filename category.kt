package com.example.proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class category : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category)
    }

    fun list_click(view: View)
    {
        val select = view as Button
        var Id = 0
        when (select.id)
        {
            R.id.Restaurantes -> Id = 1
            R.id.button2 -> Id = 2
            R.id.button3 -> Id = 3
        }
        selectCategory(Id)


    }

    fun selectCategory(id:Int)
    {
        var categoriaId = 0
        var categoriaName = ""
        val intent: Intent = Intent(this, lista::class.java)
        val bundle:Bundle = Bundle()


        when(id)
        {
            1->
            {
                categoriaId = 1
                categoriaName = "Restaurantes"
            }
            2->
            {
                categoriaId = 2
                categoriaName = "Farmacias"
            }
            3->
            {
                categoriaId = 3
                categoriaName = "Multi Tiendas"
            }

        }
        bundle.putInt("id",categoriaId)
        bundle.putString("name",categoriaName)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
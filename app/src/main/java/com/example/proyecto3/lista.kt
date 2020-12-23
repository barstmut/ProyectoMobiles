package com.example.proyecto3

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.full_service.*
import kotlinx.android.synthetic.main.lista.*
import kotlinx.android.synthetic.main.servicio.view.*

class lista : AppCompatActivity(), SensorEventListener
{
    var listavisible = ArrayList<Servicio>()

    var adapter: servAdapter? = null
    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)


        var bundle = intent.extras
        var cate = bundle!!.getInt("id")
        val name = bundle!!.getString("name")
        putServices(cate)



        tv_categoryName.text = name

        adapter = servAdapter(this, listavisible)
        gv.adapter = adapter

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    fun putServices(category:Int)
    {
        when(category)
        {
            1->
            {
                listavisible.add(Servicio("Comida","Jungle Karma Pizza",R.drawable.karma,"https://powerrangers.fandom.com/wiki/Jungle_Karma_Pizza","+18022008"))
                listavisible.add(Servicio("Comida","AngelGroove's Youth Center",R.drawable.juice,"https://powerrangers.fandom.com/wiki/Angel_Grove_Youth_Center","+28081993"))
                listavisible.add(Servicio("Comida","Shalom Grill",R.drawable.shawarma,"https://marvelcinematicuniverse.fandom.com/wiki/Shawarma_Palace","56966649869"))
            }
            2->listavisible.add(Servicio("Academia","Farmacia",R.drawable.wind,"si","56966649869"))
            3->listavisible.add(Servicio("Academia","Dou",R.drawable.wind,"si","56966649869"))

        }


    }

    override fun onSensorChanged(p0: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }


    class servAdapter : BaseAdapter {
        var listaservicios = ArrayList<Servicio>()
        var contexto: Context? = null

        constructor(contexto: Context, listaservicios: ArrayList<Servicio>) : super() {
            this.contexto = contexto
            this.listaservicios = listaservicios
        }

        override fun getCount(): Int {
            return listaservicios.size
        }

        override fun getItem(position: Int): Any {
            return listaservicios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var serv = listaservicios[position]
            var inflator = contexto!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vistaServicios = inflator.inflate(R.layout.servicio, null)

            var vibrator: Vibrator? = null

            vistaServicios.ib_item.setImageResource(serv.imagen!!)
            vistaServicios.tv_nombre.text = serv.nombre

            vistaServicios.ib_item.setOnClickListener {
                val intent = Intent(contexto, fullService::class.java)
                intent.putExtra("categoria", serv.categoria)
                intent.putExtra("nombre", serv.nombre)
                intent.putExtra("url", serv.url)
                intent.putExtra("phone", serv.phone)
                intent.putExtra("img", serv.imagen)

                contexto!!.startActivity(intent)

                val vibe: Vibrator =
                    contexto?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibe.vibrate(1000)

            }
            return vistaServicios

        }

    }
}
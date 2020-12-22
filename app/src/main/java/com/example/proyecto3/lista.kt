package com.example.proyecto3

import android.content.Context
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
import kotlinx.android.synthetic.main.lista.*
import kotlinx.android.synthetic.main.servicio.view.*

class lista : AppCompatActivity(), SensorEventListener
{
    var listaservicios = ArrayList<Servicio>()
    var adapter:servAdapter?=null

    var sensor:Sensor?=null
    var sensorManager: SensorManager?=null

    var cate:Int = 0
    var name:String?=null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)

        var bundle = intent.extras
        cate =bundle!!.getInt("id")
        name = bundle!!.getString("name")
        putServices(cate)

        tv_categoryName.text = name

        adapter = servAdapter(this,listaservicios)
        gv.adapter = adapter

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    fun putServices(category:Int)
    {
        when(cate)
        {
            1->listaservicios.add(Servicio("Academia","Academia Wind Ninja",R.drawable.wind,"si","56966649869"))
            2->listaservicios.add(Servicio("Academia","Farmacia",R.drawable.wind,"si","56966649869"))
            3->listaservicios.add(Servicio("Academia","Dou",R.drawable.wind,"si","56966649869"))

        }


    }

    class servAdapter:BaseAdapter
    {
        var listaservicios = ArrayList<Servicio>()
        var contexto: Context?=null

        constructor(contexto: Context, listaservicios:ArrayList<Servicio>):super()
        {
            this.contexto = contexto
            this.listaservicios = listaservicios
        }
        override fun getCount(): Int
        {
            return listaservicios.size
        }

        override fun getItem(position: Int): Any
        {
            return listaservicios[position]
        }

        override fun getItemId(position: Int): Long
        {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            var serv = listaservicios[position]
            var inflator = contexto!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vistaServicios = inflator.inflate(R.layout.servicio,null)

            var vibrator: Vibrator?=null

            vistaServicios.ib_item.setImageResource(serv.imagen!!)
            vistaServicios.tv_nombre.text = serv.nombre

            vistaServicios.ib_item.setOnClickListener{
                val intent = Intent(contexto,fullService::class.java)
                intent.putExtra("nombre",serv.nombre)
                intent.putExtra("url",serv.url)
                intent.putExtra("phone",serv.phone)
                intent.putExtra("img",serv.imagen)

                contexto!!.startActivity(intent)

                val vibe: Vibrator = contexto?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibe.vibrate(1000)

            }
            return vistaServicios

        }


    }

    override fun onSensorChanged(p0: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}
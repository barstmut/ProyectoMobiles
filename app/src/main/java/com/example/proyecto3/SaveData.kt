package com.example.proyecto3

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import java.util.*

class SaveData(context: Context) {


    var context: Context?= context
    var sharedRef:SharedPreferences?=null

    init {
        this.sharedRef = context.getSharedPreferences("myref",Context.MODE_PRIVATE)
    }


    fun saveData(Hora:Int, Minuto:Int) {

        val editor = sharedRef!!.edit()
        editor.putInt("hora",Hora)
        editor.putInt("minutos",Minuto)
        editor.apply()



    }

    fun getHora():Int{
        return sharedRef!!.getInt("hora",0)

    }
    fun getMinuto():Int{
        return sharedRef!!.getInt("minuto",0)

    }


    fun setAlarm() {

        val Hora = getHora()
        val Minuto= getMinuto()

        val calendario = Calendar.getInstance()
        calendario.set(Calendar.HOUR_OF_DAY,Hora)
        calendario.set(Calendar.MINUTE,Minuto)
        calendario.set(Calendar.SECOND,0)

        val intent = Intent(context,myBroadcastReceiver::class.java)
        intent.putExtra("mensaje"," tiempo de Alarma")
        intent.action = "cl.test.pdm"

        Log.i("XX",calendario.timeInMillis.toString())

        val pi = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val am =context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        am.setRepeating(AlarmManager.RTC_WAKEUP, 1608682399624,AlarmManager.INTERVAL_DAY,pi)


    }


}
package com.example.proyecto3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class myBroadcastReceiver:BroadcastReceiver(){


    override fun onReceive(context: Context?, intent: Intent?) {


        if(intent!!.action.equals("cl.test.pdm"))
        {


            val bundle = intent.extras
            Toast.makeText(context,bundle!!.getString("mensaje"),Toast.LENGTH_LONG).show()

            Log.i("XX", bundle.getString("mensaje").toString())
        }else if (intent.equals("android.intent.action.BOOT_COMPLETED")){

            val saveData = SaveData(context!!)
            saveData.setAlarm()
        }
    }


}
package com.example.proyecto3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class myBroadcastReceiver:BroadcastReceiver()
{
    private val CHANNEL_ID = "Channel_id"
    private val notificationId =101
    var vibrator: Vibrator? = null
    override fun onReceive(context: Context?, intent: Intent?)
    {
        if (context != null) {
            createNotification(context)
        }
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


    private fun createNotification(context:Context)
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            val name = "Notificacion Preciasos!!!"
            val descriptionText = "Recuerda revisar nuestros descuentos"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(context:Context)
    {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("DESCUENTOS!!!")
                .setContentText("REVISA LOS DESCUENTOS DE HOY")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context))
        {
            notify(notificationId, builder.build())
        }
    }
    fun vibrate(milisegundos:Long, amplitud:Int)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            vibrator?.vibrate(VibrationEffect.createOneShot(milisegundos,amplitud))

        }
    }

}
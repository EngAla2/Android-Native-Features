package com.example.testandroidfetures.tryandroidnotif

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.testandroidfetures.R

fun createNotificationChannel(id:String, name:String, descriptionText:String, activity:Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun create_notif(context: Context, id:String, Title:String, Text:String){
    val intent = Intent(context, NotifActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val builder = NotificationCompat.Builder(context, id)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(Title)
        .setContentText(Text)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Set the intent that will fire when the user taps the notification
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(0, builder.build())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun delete_nitif_channel(id:String, activity:Activity){
    val notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.deleteNotificationChannel(id)
}

@RequiresApi(Build.VERSION_CODES.O)
fun fill_drop_down_list(context: Context, spinner:Spinner, activity: Activity){
    var notifications =get_list_of_all_notifications(context)
    if (spinner != null) {
        val adapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, notifications)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(activity,
                    activity.getString(R.string.selected_item) + " " +
                            "" + notifications[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun get_list_of_all_notifications(context: Context): MutableList<String> {
    var notifications =  mutableListOf<String>()
    with(NotificationManagerCompat.from(context)) {
        var Notifications=getNotificationChannels()
        for( a in Notifications){
            notifications.add(a.id)
        }
    }
    return notifications
}

package com.example.testandroidfetures.tryandroidnotif.client

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationManagerCompat
import com.example.testandroidfetures.R
import com.example.testandroidfetures.tryandroidnotif.adapter.ListViewModel
import com.example.testandroidfetures.tryandroidnotif.adapter.ListViewModelAdapter
import com.example.testandroidfetures.tryandroidnotif.NotifActivity
import com.example.testandroidfetures.tryandroidnotif.get_list_of_all_notifications


class Client : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private val sharedPreferences: SharedPreferences = this.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        val btnSave = findViewById<Button>(R.id.save)
        val text = findViewById<TextView>(R.id.textView4)


        val androidId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )

        btnSave.setOnClickListener(View.OnClickListener {
            startActivity( Intent(this, NotifActivity::class.java))
        })

        if  (!NotificationManagerCompat.from(this).areNotificationsEnabled() and sharedPreferences.getBoolean("first_time", true)){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("NOTIFICATIONS")
            builder.setMessage("Your NOTIFICATIONS are disabled, allow them from settings!")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, this.getPackageName())
                    .putExtra(Settings.EXTRA_CHANNEL_ID, "id")
                    startActivity(intent)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Maybe") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Maybe", Toast.LENGTH_SHORT).show()
            }
            editor.putBoolean("first_time", false)
            builder.show()
        }

        val listView = findViewById<ListView>(R.id.dynamic_list)

        var listViewAdapter = ListViewModelAdapter(this, getListViewModelList())
        listView.adapter = listViewAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
        }

     }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <ArrayList> getListViewModelList(): ArrayList {
        var listViewModelArrayList = ArrayList<ListViewModel>()

        var notifications = get_list_of_all_notifications(this)
        for (id in notifications)
            listViewModelArrayList.add(ListViewModel(1, id, sharedPreferences.getBoolean(id, false)))
        return listViewModelArrayList as ArrayList
    }
}
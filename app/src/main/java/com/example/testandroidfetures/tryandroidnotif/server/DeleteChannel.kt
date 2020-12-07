package com.example.testandroidfetures.tryandroidnotif.server

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.testandroidfetures.R
import com.example.testandroidfetures.tryandroidnotif.NotifActivity
import com.example.testandroidfetures.tryandroidnotif.delete_nitif_channel
import com.example.testandroidfetures.tryandroidnotif.fill_drop_down_list

class DeleteChannel : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_channel)

        val del_notif_ch = findViewById<Button>(R.id.delete)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val text = findViewById<TextView>(R.id.textView)
        fill_drop_down_list(this, spinner, this)

        del_notif_ch.setOnClickListener {
            delete_nitif_channel(id=spinner.getSelectedItem().toString(), activity=this)
            startActivity(Intent(this, NotifActivity::class.java))

        }
    }
}
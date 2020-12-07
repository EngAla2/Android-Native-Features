package com.example.testandroidfetures.tryandroidnotif.server
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.testandroidfetures.R

class ServerSide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_side)
        val sharedPrefFile = "kotlinsharedpreference"

        val create_notif = findViewById<Button>(R.id.create_notif)
        val del_notif_ch = findViewById<Button>(R.id.del_ch)
        val notif = findViewById<Button>(R.id.Notify)


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )

        create_notif.setOnClickListener {
            startActivity(Intent(this, CreateNotif::class.java))
        }

        del_notif_ch.setOnClickListener {
                startActivity(Intent(this, DeleteChannel::class.java))
        }


        notif.setOnClickListener {
            startActivity(Intent(this, CreateChannel::class.java))
        }
    }
}
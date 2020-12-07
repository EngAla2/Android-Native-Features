package com.example.testandroidfetures.tryandroidnotif

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroidfetures.R
import com.example.testandroidfetures.tryandroidnotif.client.Client
import com.example.testandroidfetures.tryandroidnotif.server.ServerSide

class NotifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notif_main)
        val client_side = findViewById<Button>(R.id.client)
        val server_side = findViewById<Button>(R.id.server)

        client_side.setOnClickListener {
            startActivity(Intent(this, Client::class.java))
        }
        server_side.setOnClickListener {
            startActivity( Intent(this, ServerSide::class.java))
        }
    }

}

package com.example.testandroidfetures.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroidfetures.R


class WiFi : AppCompatActivity() {
    lateinit var enableButton:Button
    lateinit var disableButton:Button
//    lateinit var  buttonScan: Button

    private var wifiManager: WifiManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wi_fi)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (!wifiManager!!.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ...", Toast.LENGTH_LONG).show()
        }


        enableButton = findViewById<Button>(R.id.button1)
        disableButton = findViewById<Button>(R.id.button2)


        enableButton.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                startActivityForResult(panelIntent, 0)
            } else {
                // add appropriate permissions to AndroidManifest file
                wifiManager!!.setWifiEnabled(true)
                Toast.makeText(this, "WiFi Enabled ...", Toast.LENGTH_LONG).show()}
        }


        disableButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                startActivityForResult(panelIntent, 0)
            } else {
                wifiManager!!.setWifiEnabled(false)
                Toast.makeText(this, "WiFi disabled ...", Toast.LENGTH_LONG).show()}
        }
    }

}
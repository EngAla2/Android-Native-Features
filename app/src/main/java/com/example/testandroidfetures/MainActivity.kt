package com.example.testandroidfetures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.testandroidfetures.camera2video.CameraActivity
import com.example.testandroidfetures.SMS.SendSMS
import com.example.testandroidfetures.SoeechToText.TextToSpeech
import com.example.testandroidfetures.audio.AudioActivity
import com.example.testandroidfetures.bluetooth.Bluetooth
import com.example.testandroidfetures.location.LocationRequest
import com.example.testandroidfetures.network.WiFi
import com.example.testandroidfetures.screenRecord.ScreenRecord
import com.example.testandroidfetures.testToSpeech.SpeechToText
import com.example.testandroidfetures.tryandroidnotif.NotifActivity
import com.example.testandroidfetures.vibration.Vibration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Test Vibration
        val vibrate = findViewById<Button>(R.id.tset_vibration)
        vibrate.setOnClickListener {
            startActivity(Intent(this, Vibration::class.java) )
        }

        //        Test Wifi
        val wifi = findViewById<Button>(R.id.test_wifi)
        wifi.setOnClickListener {
            startActivity(Intent(this, WiFi::class.java) )
        }
        //        Test Notification
        val notif = findViewById<Button>(R.id.test_notifications)
        notif.setOnClickListener {
            startActivity(Intent(this, NotifActivity::class.java) )
        }
        //        Test Notification
        val screenRecord = findViewById<Button>(R.id.test_screen_secord)
        screenRecord.setOnClickListener {
            startActivity(Intent(this, ScreenRecord::class.java) )
        }
        //        Test location
        val location = findViewById<Button>(R.id.test_gps)
        location.setOnClickListener {
            startActivity(Intent(this, LocationRequest::class.java) )
        }

        //        Test Notification
        val bluetooth = findViewById<Button>(R.id.test_bluetooth)
        bluetooth.setOnClickListener {
            startActivity(Intent(this, Bluetooth::class.java) )
        }

        // Test SMS
        val sms = findViewById<Button>(R.id.sms)
        sms.setOnClickListener {
            startActivity(Intent(this, SendSMS::class.java) )
        }

        // Test Audio
        val test_aoudio_record = findViewById<Button>(R.id.test_aoudio_record)
        test_aoudio_record.setOnClickListener {
            startActivity(Intent(this, AudioActivity::class.java) )
        }
        val vediorec = findViewById<Button>(R.id.camreavedio)
        vediorec.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java) )
        }
        // Text To Speech Audio
        val text_to_speach = findViewById<Button>(R.id.text_to_speach)
        text_to_speach.setOnClickListener {
            startActivity(Intent(this, TextToSpeech::class.java) )
        }
        // Speech To Text Audio
        val speech_to_text = findViewById<Button>(R.id.speech_to_text)
        speech_to_text.setOnClickListener {
            startActivity(Intent(this, SpeechToText::class.java) )
        }
    }
}
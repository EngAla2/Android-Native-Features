package com.example.testandroidfetures.vibration

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageButton
import com.example.testandroidfetures.R
import java.util.concurrent.TimeUnit

class Vibration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibration)
        val vibrator = findViewById<ImageButton>(R.id.vibrate)

        vibrator.setOnClickListener {
            vibratePhone()
        }
    }
    fun vibratePhone() {

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(300)
        }
    }
}
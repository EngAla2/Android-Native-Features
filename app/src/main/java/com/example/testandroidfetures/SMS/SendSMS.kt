package com.example.testandroidfetures.SMS

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.testandroidfetures.R
import com.example.testandroidfetures.screenRecord.ScreenRecord
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_screen_record.*
import kotlinx.android.synthetic.main.activity_send_s_m_s.*
import java.lang.Exception


class SendSMS : AppCompatActivity() {
    val REQUEST_PERMISSIONM = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_s_m_s)

        if(ContextCompat.checkSelfPermission(this , Manifest.permission.SEND_SMS) + ContextCompat.checkSelfPermission(this , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                Snackbar.make(rootLayout, "Permissions", Snackbar.LENGTH_INDEFINITE)
                    .setAction("ENABLE") {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(
                                Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE
                            ),
                            REQUEST_PERMISSIONM
                        )
                    }.show()
            }
            else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE),
                    REQUEST_PERMISSIONM
                )
            }
        }

        var sms = SmsManager.getDefault()
        SendSms.setOnClickListener {
            try{
            sms.sendTextMessage(phoneNum.text.toString(), null, Text.text.toString(), null, null)}
            catch (e:Exception){
                Text.setText(e.toString())
            }
        }

        call.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum.text.toString()))
                startActivity(intent)}
            catch (e:Exception){
                Text.setText(e.toString())
            }
        }
    }

}
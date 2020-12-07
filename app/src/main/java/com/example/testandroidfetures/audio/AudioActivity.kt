package com.example.testandroidfetures.audio

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.testandroidfetures.R
import com.example.testandroidfetures.audio.adapter.ListViewModel
import com.example.testandroidfetures.audio.adapter.ListViewModelAdapter
import java.io.File
import java.io.IOException
import java.lang.Exception

@RequiresApi(api = Build.VERSION_CODES.M)
class AudioActivity : AppCompatActivity() {
    var play : Button? = null
    var stop : Button? = null
    var record: Button? = null
    var record_name: EditText? = null
    val RECORD_AUDIO_REQUEST_CODE = 111
    val root = android.os.Environment.getExternalStorageDirectory()
    val file = File(root.absolutePath + "/AndroidCodility/Audios")
    var recordArrayList = ArrayList<ListViewModel>()
    var listView: ListView? = null
    var recordList = ArrayList<ListViewModel>()
    var name : String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        play = findViewById<Button>(R.id.play)
        stop = findViewById<Button>(R.id.stop)
        record = findViewById<Button>(R.id.record)
        listView = findViewById<ListView>(R.id.dynamic_list)
        record_name = findViewById<EditText>(R.id.record_name)

        if (!file.exists()) {
            file.mkdirs()
        }
        var mr = MediaRecorder()
        play?.isEnabled = false
        record?.isEnabled = false
        stop?.isEnabled = false
        getPermissionToRecordAudio()
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), RECORD_AUDIO_REQUEST_CODE)
        else record?.isEnabled = true

        fillListViwe()
//        Start recording

        record?.setOnClickListener{
            try{
                record?.isEnabled = false
                play?.isEnabled = false

                name = System.currentTimeMillis().toString()
                mr = MediaRecorder()
                mr!!.setAudioSource(MediaRecorder.AudioSource.MIC)
                mr!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                val root = android.os.Environment.getExternalStorageDirectory()
                val file = File(root.absolutePath + "/AndroidCodility/Audios")
                if (!file.exists()) { file.mkdirs() }

                val fileName = root.absolutePath + "/AndroidCodility/Audios/" + (name + ".mp3")
                Log.d("filename", fileName)
                mr!!.setOutputFile(fileName)
                mr!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

                try {
                    mr!!.prepare()
                    mr!!.start()
                } catch (e: IOException) {e.printStackTrace()}
                stop?.isEnabled = true
                fillListViwe()
            }
            catch (e : Exception){
                println("#####====================###########===============################")
                println(e.toString())
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()

            }
        }
//        Stop recording
        stop?.setOnClickListener {

            try {
                mr!!.stop()
                mr!!.release()
                play?.isEnabled = true
                stop?.isEnabled = false
                record?.isEnabled = true
                fillListViwe()
            }catch (e: Exception){
                println(e.toString())
                e.printStackTrace()
            }
        }
        play?.setOnClickListener {

            playRecord(name,file.toString() + "/$name.mp3" )
        }
    }

    private fun getPermissionToRecordAudio() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid checking the build version since Context.checkSelfPermission(...) is only available in Marshmallow
        // 2) Always check for permission (even if permission has already been granted) since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE), RECORD_AUDIO_REQUEST_CODE)
        }
    }

    // Callback with the request from calling requestPermissions(...)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.size == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Record Audio permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You must give permissions to use this app. App is exiting.", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }
    }
    public fun fillListViwe() {
        val recordArrayList = ArrayList<ListViewModel>()
        val root = android.os.Environment.getExternalStorageDirectory()
        val path = root.absolutePath + "/AndroidCodility/Audios"
        val directory = File(path)
        val files = directory.listFiles()
        if (files != null) {
            for (i in files.indices) {
                val fileName = files[i].name
                val recordingUri = root.absolutePath + "/AndroidCodility/Audios/" + fileName
                recordArrayList.add(ListViewModel(file.toString() + "/$fileName", fileName))

            }
            var listViewAdapter = ListViewModelAdapter(this, recordArrayList as ArrayList)
            listView?.adapter = listViewAdapter
            listView?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                fillListViwe()
            }
        }
    }

}
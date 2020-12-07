package com.example.testandroidfetures.audio

import android.media.MediaPlayer
import android.util.Log
import java.io.File


private var mPlayer: MediaPlayer? = null

public fun playRecord(name: String = "", paths: String?){
    mPlayer = MediaPlayer()
    try {
        mPlayer!!.setDataSource(paths)
        mPlayer!!.prepare()
        mPlayer!!.start()
    } catch (e: Exception) {

        println(e.toString())
        Log.e("LOG_TAG", "prepare() failed")
    }
}
fun deleteRecord(name: String, paths: String?){
    try {
    val fdelete: File = File(paths)
    if (fdelete.exists()) {
        println(fdelete.delete())
    }
        }catch (e: Exception){
            println(e.toString())
        Log.e("LOG_TAG", "delete() failed")
        }
}

package com.example.testandroidfetures.audio

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testandroidfetures.MainActivity
import java.io.File


private var mPlayer: MediaPlayer? = null

public fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}
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
fun deleteRecord(name: String, paths: String?, view: View?){
    try {
    val fdelete: File = File(paths)
//        if (view != null) {
//            val myIntent = Intent(view.context, AudioActivity::class.java)
//            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//            view.context.applicationContext.startActivity(myIntent)
//        }
        val mActivity = AudioActivity() // BIG NO TO THIS.


        if (fdelete.exists()) {
        println(fdelete.delete())
            mActivity.fillListViwe()
        }
        }catch (e: Exception){
            println(e.toString())
        Log.e("LOG_TAG", "delete() failed")
        }
}


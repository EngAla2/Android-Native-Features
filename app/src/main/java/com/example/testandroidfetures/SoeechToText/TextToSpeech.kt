package com.example.testandroidfetures.SoeechToText

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testandroidfetures.R
import kotlinx.android.synthetic.main.activity_text_to_speech.*
import android.speech.tts.TextToSpeech
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class TextToSpeech : AppCompatActivity() {

    //Text To Speech
    lateinit var mTTS:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Text To Speech"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language

                mTTS.language = Locale.US
                mTTS.voice = mTTS.voices.random()
            }
        })

        //speak button click
        speakBtn.setOnClickListener {
            //get text from edit text
            val toSpeak = textEt.text.toString()
            if (toSpeak == ""){
                //if there is no text in edit text
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }
            else{
                //if there is text in edit text
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
                mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }

        //stop speaking button click
        stopBtn.setOnClickListener {
            if (mTTS.isSpeaking){
                //if speaking then stop
                mTTS.stop()
                //mTTS.shutdown()
            }
            else{
                //if not speaking
                Toast.makeText(this, "Not speaking", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        if (mTTS.isSpeaking){
            //if speaking then stop
            mTTS.stop()
            //mTTS.shutdown()
        }
        super.onPause()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
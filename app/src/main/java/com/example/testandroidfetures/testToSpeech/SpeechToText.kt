package com.example.testandroidfetures.testToSpeech

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.testandroidfetures.R
import java.util.*
import kotlinx.android.synthetic.main.activity_speech_to_text.*

class SpeechToText : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_STT = 1
    }
    private val textToSpeechEngine: TextToSpeech by lazy {
        TextToSpeech(this,
            TextToSpeech.OnInitListener { status ->
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechEngine.language = Locale.US
                }
                else{
                    Toast.makeText(this, "Something went wrong !", Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text)


        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Speech To Text"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)


        btn_stt.setOnClickListener {
            val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            sttIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")

            try {
                startActivityForResult(sttIntent, REQUEST_CODE_STT)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Your device does not support STT.", Toast.LENGTH_LONG).show()
            }
        }

        btn_tts.setOnClickListener {
            val text = et_text_input.text.toString().trim()
            if (text.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
                } else {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }
            } else {
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_STT -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        val recognizedText = result!![0]
                        et_text_input.setText(recognizedText)

                }
            }
        }
    }

    override fun onPause() {
        textToSpeechEngine.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeechEngine.shutdown()
        super.onDestroy()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

package com.nizamalfian.espressoforuitesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchSecondActivity(@Suppress("UNUSED_PARAMETER") view: View) {
        Log.d(LOG_TAG, "Button clicked!")
        SecondActivity.launch(this, editText_main?.text.toString(), TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        editText_main?.setText("")

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                text_header_reply.visible()
                text_message_reply?.run {
                    text = data?.getStringExtra(SecondActivity.EXTRA_REPLY)
                    visible()
                }
            }
        }
    }

    companion object {
        private val LOG_TAG = MainActivity::class.java.canonicalName
        private const val TEXT_REQUEST = 1
    }
}

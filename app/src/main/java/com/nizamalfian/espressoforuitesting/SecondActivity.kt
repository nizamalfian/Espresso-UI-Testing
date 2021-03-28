package com.nizamalfian.espressoforuitesting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setData()
    }

    private fun setData() {
        text_message?.text = intent?.getStringExtra(EXTRA_MESSAGE)
    }

    fun returnReply(@Suppress("UNUSED_PARAMETER") view: View) {
        setResult(
            RESULT_OK,
            Intent().apply { putExtra(EXTRA_REPLY, editText_second?.text?.toString()) }
        )
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"
        private const val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"

        private fun getIntent(activity: AppCompatActivity, message: String): Intent {
            return Intent(activity, SecondActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
        }

        fun launch(activity: AppCompatActivity,
                   message: String,
                   requestCode: Int,
                   intent: Intent = getIntent(activity, message)) {
            activity.startActivityForResult(intent, requestCode)
        }
    }
}

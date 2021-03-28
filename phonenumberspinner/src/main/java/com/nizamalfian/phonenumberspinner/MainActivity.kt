package com.nizamalfian.phonenumberspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    private fun initComponent() {
        label_spinner?.apply {
            onItemSelectedListener = this@MainActivity
            adapter = ArrayAdapter.createFromResource(
                context,
                R.array.labels_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }
    }

    private fun showText(spinnerLabel: String) {
        text_phonelabel?.apply {
            val showString = "${editText_main?.text} - $spinnerLabel"
            Toast.makeText(context, showString, Toast.LENGTH_SHORT).show()
            text = showString
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(TAG, "nothing selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected with position $position")
        showText(parent?.getItemAtPosition(position)?.toString() ?: "")
    }

    companion object {
        private val TAG = MainActivity::class.java.canonicalName
    }
}

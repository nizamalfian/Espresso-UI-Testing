package com.nizamalfian.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizamalfian.myrecyclerview.WordViewHolder.Companion.Callback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, Callback {
    private val words: LinkedList<String> by lazy {
        LinkedList<String>().apply {
            // Put initial data into the word list.
            for (i in 0 until MAX_ITEM) {
                addLast("Word $i")
            }
        }
    }

    private val wordAdapter: WordAdapter by lazy {
        WordAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        loadData()
    }

    private fun initComponent() {
        setSupportActionBar(toolbar)
        recyclerView?.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(context)
        }
        fab?.setOnClickListener(this)
    }

    private fun loadData() {
        wordAdapter.updateData(words)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            fab.id -> {
                val wordsSize = wordAdapter.itemCount
                wordAdapter.addData("+ Word + $wordsSize")
                recyclerView.smoothScrollToPosition(wordsSize)
            }
        }
    }

    override fun onItemClicked(position: Int) {
        wordAdapter.updateData(position, "Clicked! ${wordAdapter.getData().getOrNull(position) ?: ""}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) return true

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MAX_ITEM = 20
    }
}

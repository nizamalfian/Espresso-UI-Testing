package com.nizamalfian.scorekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var score1: Int = 0
    private var score2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData(savedInstanceState)
    }

    private fun setData(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            score1 = it.getInt(STATE_SCORE_1)
            score2 = it.getInt(STATE_SCORE_2)

            score_1.text = score1.toString()
            score_2.text = score2.toString()
        }
    }

    fun decreaseScore(@Suppress("UNUSED_PARAMETER") view: View) {
        when (view.id) {
            decreaseTeam1.id -> {
                score1 -= 1
                score_1.text = score1.toString()
            }
            decreaseTeam2.id -> {
                score2 -= 1
                score_2.text = score2.toString()
            }
        }
    }

    fun increaseScore(@Suppress("UNUSED_PARAMETER") view: View) {
        when (view.id) {
            increaseTeam1.id -> {
                score1 += 1
                score_1.text = score1.toString()
            }
            increaseTeam2.id -> {
                score2 += 1
                score_2.text = score2.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode)
        else
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        recreate()

        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, score1)
        outState.putInt(STATE_SCORE_2, score2)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }
}

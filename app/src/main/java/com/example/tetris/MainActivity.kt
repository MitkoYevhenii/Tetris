package com.example.tetris

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.tetris.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBar:ActionBar? = supportActionBar
        appBar?.hide()

        // Events Listeners Main Activity
        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScore = findViewById<Button>(R.id.btn_reset_score)
        val btnSettings = findViewById<Button>(R.id.btn_settings)
        val btnExit: Button = findViewById<Button>(R.id.btn_exit)
        var tvHighScore = findViewById<TextView>(R.id.tv_high_score)

        // Calling Functions Main Activity
        btnNewGame.setOnClickListener(this::onBtnNewGameClick)
        btnResetScore.setOnClickListener(this::onBtnResetScoreClick)
        btnSettings.setOnClickListener(this::onBtnSettingClick)
        btnExit.setOnClickListener(this::onBtnExitClick)


    }

    private fun onBtnNewGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun onBtnResetScoreClick(view:  View) {
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        Snackbar.make(view, "Score successfully reset",
            Snackbar.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.tv_high_score).text = "High score: ${preferences.clearHighScore()}"
    }

    private fun onBtnSettingClick(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnExitClick(view: View) {
        exitProcess(0)
    }
}
package com.example.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tetris.storage.AppPreferences

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrentScore: TextView? = null
    var appPreferences: AppPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPreferences = AppPreferences(this)

        val btnRestart = findViewById<Button>(R.id.btn_restart)
        val tvHighScore = findViewById<TextView>(R.id.tv_high_score)
        val tvCurrentScore = findViewById<TextView>(R.id.tv_current_score)

        updateHighScore()
        updateCurrentScore()
    }

    // High score update function
    private fun updateHighScore() {
        tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    //Current score update function
    private fun updateCurrentScore() {
        tvCurrentScore?.text = "0"
    }
}
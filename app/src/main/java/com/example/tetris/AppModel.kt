package com.example.tetris

import androidx.annotation.StringRes
import com.example.tetris.constants.FieldConstants
import com.example.tetris.helpers.array2dOfByte
import com.example.tetris.models.Block
import com.example.tetris.storage.AppPreferences

class AppModel {

    // Current score in game session
    var score: Int = 0

    // Contains AppPreferences for SharedPreferences access
    private var preferences: AppPreferences? = null

    // The current broadcast block to access the playing field
    var currentBlock: Block? = null

    // Current Game state
    var currentState: String = Statuses.AWAITING_START.name

    private var field: Array<ByteArray> = array2dOfByte(
        FieldConstants.ROW_COUNT.value,
        FieldConstants.COLUMN_COUNT.value
    )

    enum class Statuses {
        AWAITING_START, ACTIVE, INACTIVE, OVER
    }

    enum class Motions {
        LEFT, RIGHT, DOWN, ROTATE
    }

    fun setPreferences(preferences: AppPreferences?) {
        this.preferences = preferences
    }

    fun getCellStatus(row: Int, column: Int): Byte? {
        return field[row][column]
    }

    fun setCellStatus(row: Int, column: Int, status: Byte?) {
        if (status != null) {
            field[row][column] = status
        }
    }

    fun isGameOver() : Boolean {
        return currentState == Statuses.OVER.name
    }

    fun isGameActive(): Boolean {
        return currentState == Statuses.AWAITING_START.name
    }

    enum class CellConstants(val value: Byte) {
        EMPTY(0), EPHEMERAL(1)
    }

    private fun boostScope() {
        score += 10
        if(score > preferences?.getHighScore() as Int){
            preferences?.saveHighScore(score)
        }
    }

    private fun generateNextBlock() {
        currentBlock = Block.createBlock()
    }
}
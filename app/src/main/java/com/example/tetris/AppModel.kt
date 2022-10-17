package com.example.tetris

import android.graphics.Point
import com.example.tetris.constants.CellConstants
import com.example.tetris.constants.FieldConstants
import com.example.tetris.helpers.array2dOfByte
import com.example.tetris.models.Block
import com.example.tetris.storage.AppPreferences
import kotlin.math.truncate


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

    // AWAITING_START - game state before launch
    // ACTIVE,  INACTIVE - game state
    // OVER - The status of the game at the time of its completion
    enum class Statuses {
        AWAITING_START, ACTIVE, INACTIVE, OVER
    }

    //Components responsible for the movement of blocks
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

    //Increases the player's score
    private fun boostScope() {
        score += 10
        if(score > preferences?.getHighScore() as Int){
            preferences?.saveHighScore(score)
        }
    }

    private fun generateNextBlock() {
        currentBlock = Block.createBlock()
    }

    private fun validTranslation(position: Point, shape: Array<ByteArray>): Boolean {
        if (position.y < 0 || position.x < 0) {
            return false
        } else if (position.y + shape.size > FieldConstants.ROW_COUNT.value) {
            return false
        } else if (position.x + shape[0].size > FieldConstants.COLUMN_COUNT.value) {
            return false
        } else {
            for (i in 0 until shape.size) {
                for (j in 0 until shape[i].size){
                    val y = position.y + i
                    val x = position.x + j
                    if (CellConstants.EMPTY.value != shape[i][j] && CellConstants.EMPTY.value != field[y][x]) {
                        return false
                    }
                }
            }
        }
        return true
    }

//    private fun moveValid(position: Point, frameNumber: Int?): Boolean {
//        val shape: Array<ByteArray>? = currentBlock?.getShape(frameNumber as Int)
//        return validTranslation(position, shape as Array<ByteArray>)
//    }
//
//    generateField(action: String) {
//        if (isGameActive()) {
//            resetField()
//            var frameNumber: Int? = currentBlock?.frameNumber
//            val coordinate: Point? = Point()
//
//            when(action) {
//                Motions.LEFT.name -> {
//                    coordinate?.x = currentBlock?.position?.x?.minus(1)
//                }
//                Motions.RIGHT.name -> {
//                    coordinate?.x = currentBlock?.position?.x?.plus(1)
//                }
//                Motions.DOWN.name -> {
//                    coordinate.y = currentBlock?.position?.y?.plus(1)
//                }
//                Motions.ROTATE.name -> {
//                    coordinate.y = flameNumber?.plus(1)
//                if (frameNumber != null) {
//                    if(frameNumber >= currentBlock?.frameNumber as Int){
//                        frameNumber = 0
//                    }
//                }
//            }
//        }
//    }
}
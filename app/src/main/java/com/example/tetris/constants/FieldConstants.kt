package com.example.tetris.constants

enum class FieldConstants(val value: Int) {
    COLUMN_COUNT(10), ROW_COUNT(20);
}

enum class CellConstants(val value: Byte) {
    EMPTY(0), EPHEMERAL(1)
}
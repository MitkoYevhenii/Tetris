package com.example.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.tetris.constants.FieldConstants;

import java.lang.reflect.Field;
import java.util.Random;


public class Block {
    // Block shape index
    private int shapeIndex;

    // Number of frames(a single frame in the video stream.)
    private int frameNumber;

    // Block color
    private BlockColor color;

    // Point location
    private Point position;

    // Initialisation class Block
    private Block(int shapeIndex, BlockColor blockColor) {
        this.frameNumber = 0;
        this.shapeIndex = shapeIndex;
        this.color = blockColor;
        this.position = new Point(FieldConstants.COLUMN_COUNT.getValue()/2, 0);
    }

    // Create random shapeIndex for class Block
    public static Block createBlock() {
        Random random = new Random();
        int shapeIndex = random.nextInt(Shape.values().length);
        BlockColor blockColor = BlockColor.values()
                [random.nextInt(BlockColor.values().length)];

        Block block = new Block(shapeIndex, blockColor);
        block.position.x = block.position.x - Shape.values()
                [shapeIndex].getStartPosition();
        return block;
    }

    // Creating Colors for a our Block
    public enum BlockColor {
        PINK(Color.rgb(255, 105, 180), (byte) 2),
        GREEN(Color.rgb(0, 128, 0), (byte) 3),
        ORANGE(Color.rgb(255, 140, 0), (byte) 4),
        YELLOW(Color.rgb(255, 255, 0), (byte) 5),
        CYAN(Color.rgb(0, 255, 255), (byte) 6);

        BlockColor(int rgbValue, byte value) {
            this.rgbValue = rgbValue;
            this.byteValue = value;
        }
        private final int rgbValue;
        private final byte byteValue;

    }

    ///////////////////////////////////////

    // Method that returns the color rgbValue
    public static int getColor(byte value) {
        for (BlockColor color : BlockColor.values()) {
            if (value == color.byteValue) {
                return color.rgbValue;
            }
        }
        return -1;
    }

    @NonNull
        public final byte[][] getShape(int frameNumber) {
        return Shape.values()[shapeIndex].getFrame(frameNumber).as2dByteArray();
    }

    // Method that returns the position of the Block
    public Point getPosition() {
        return this.position;
    }

    // Method that returns a Block frame
    public final int getFrameCount() {
        return Shape.values()[shapeIndex].getFrameCount();
    }


    public int getFrameNumber() {
        return frameNumber;
    }

    // Method that returns the color of the Block
    public int getColor() {
        return  color.rgbValue;
    }

    // Method that returns byteValue
    public byte getStaticValue() {
        return color.byteValue;
    }
}

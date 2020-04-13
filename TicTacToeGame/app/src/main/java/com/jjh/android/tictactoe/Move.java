package com.jjh.android.tictactoe;

import androidx.annotation.NonNull;

public class Move {

    private final int x;
    private final int y;
    private final Counter counter;

    public Move(int x, int y, Counter counter) {
        this.x = x;
        this.y = y;
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    @NonNull
    public String toString() {
        return "Move(" + x + ", " + y + ": " + counter + ")";
    }

}

package com.jjh.android.tictactoe;

public class Move {

    private int x;
    private int y;
    private Counter counter;

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
    public String toString() {
        return "Move(" + x + ", " + y + ": " + counter + ")";
    }

}

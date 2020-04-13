package com.jjh.android.tictactoe;

public class Player {

    protected Counter counter;

    public Player(Counter counter) {
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player(");
        sb.append(counter);
        sb.append(')');
        return sb.toString();
    }

    public boolean isAutomatedPlayer() {
        return false;
    }
}

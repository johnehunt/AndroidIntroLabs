package com.jjh.android.tictactoe;

import androidx.annotation.NonNull;

public class Player {

    protected final Counter counter;

    public Player(Counter counter) {
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }

    @Override
    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player(");
        sb.append(counter);
        sb.append(')');
        return sb.toString();
    }

    public boolean isAutomatedPlayer() {
        return false;
    }
}

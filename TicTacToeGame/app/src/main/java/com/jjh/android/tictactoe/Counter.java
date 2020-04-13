package com.jjh.android.tictactoe;

import androidx.annotation.NonNull;

public class Counter {

    public static final String X = "X";
    public static final String Y = "O";

    private String label;

    public Counter(String label) {
        if (label.equals(X) || label.equals(Y)) {
            this.label = label;
        } else {
            throw new GameException("Counter Label must be " + X + " or " + Y);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.label;
    }

}

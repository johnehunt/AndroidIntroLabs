package com.jjh.android.tictactoe;

public class GameException extends RuntimeException {

    public GameException() { }

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}

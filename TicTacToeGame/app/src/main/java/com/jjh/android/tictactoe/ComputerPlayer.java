package com.jjh.android.tictactoe;

import android.util.Log;

import java.util.Random;

public class ComputerPlayer extends Player {

    private Board board;
    private Random random = new Random();

    public ComputerPlayer(Counter counter, Board board) {
        super(counter);
        this.board = board;
    }

    public Move randomlySelectMove() {
        Log.d(this.getClass().getSimpleName(), "randomlySelectMove()");
        // Use a simplistic random selection approach
        // to find a cell to fill.
        Move move = null;
        while (move == null) {
            // Randomly select the cell
            int row = random.nextInt(2);
            int column = random.nextInt(2);
            Log.d(this.getClass().getSimpleName(), "randomlySelectMove() - " + row + ", " + column);
            // Check to see if the cell is empty
            if (board.isCellEmpty(row, column)) {
                move = new Move(row, column, counter);
            }
        }
        return move;
    }

    public Move getMove() {
        Log.d(this.getClass().getSimpleName(), "getMove()");
        // Provide a very simple algorithm for selecting a move
        if (board.isCellEmpty(1, 1)) {
            // Choose the center
            return new Move(1, 1, counter);
        } else if (board.isCellEmpty(0, 0)) {
            // Choose the top left
            return new Move(0, 0, counter);
        } else if (board.isCellEmpty(2, 2)) {
            // Choose the bottom right
            return new Move(2, 2, counter);
        } else if (board.isCellEmpty(0, 2)) {
            // Choose the top right
            return new Move(0, 2, counter);
        } else if (board.isCellEmpty(0, 2)) {
            // Choose the top right
            return new Move(2, 0, counter);
        } else {
            return randomlySelectMove();
        }
    }

    @Override
    public boolean isAutomatedPlayer() {
        return true;
    }

}

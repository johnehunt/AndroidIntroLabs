package com.jjh.android.tictactoe;

import android.util.Log;

import java.util.Random;

public class Board {

    private Counter[][] cells = new Counter[3][3];

    private Player firstPlayer;
    private Player humanPlayer;
    private Player computerPlayer;
    private Random rand = new Random();

    public Board() {
        Log.d(this.getClass().getSimpleName(), "constructor()");
        // Randomly allocate user to X or O
        int r = rand.nextInt(100);
        if (r > 49) {
            firstPlayer = new Player(new Counter(Counter.X));
            humanPlayer = firstPlayer;
            computerPlayer = new ComputerPlayer(new Counter(Counter.Y), this);
        } else {
            firstPlayer = new ComputerPlayer(new Counter(Counter.Y), this);
            computerPlayer = firstPlayer;
            humanPlayer = new Player(new Counter(Counter.X));
        }
    }

    public void addMove(Move move) {
        Log.d(this.getClass().getSimpleName(), "addMove(" + move + ")");
        Counter[] row = cells[move.getX()];
        row[move.getY()] = move.getCounter();
    }

    public boolean isCellEmpty(Counter counter) {
        return counter == null;
    }

    public boolean isCellEmpty(int row, int col) {
        Counter counter = cells[row][col];
        return counter == null;
    }

    public boolean cellContains(Counter counter, int row, int column) {
        return cells[row][column] == counter;
    }

    public boolean isFull() {
        for (Counter[] row : cells) {
            for (Counter c : row) {
                if (isCellEmpty(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForWinner(Player player) {
        Log.d(this.getClass().getSimpleName(), "checkForWinner(" + player + ")");
        Counter c = player.getCounter();
        return (// Across the top
                (cellContains(c, 0, 0) && cellContains(c, 0, 1) && cellContains(c, 0, 2)) ||
                        // Across the middle
                        (cellContains(c, 1, 0) && cellContains(c, 1, 1) && cellContains(c, 1, 2)) ||
                        // Across the bottom
                        (cellContains(c, 2, 0) && cellContains(c, 2, 1) && cellContains(c, 2, 2)) ||
                        // down the left side
                        (cellContains(c, 0, 0) && cellContains(c, 1, 0) && cellContains(c, 2, 0)) ||
                        // down the middle
                        (cellContains(c, 0, 1) && cellContains(c, 1, 1) && cellContains(c, 2, 1)) ||
                        // down the right side
                        (cellContains(c, 0, 2) && cellContains(c, 1, 2) && cellContains(c, 2, 2)) ||
                        // diagonal
                        (cellContains(c, 0, 0) && cellContains(c, 1, 1) && cellContains(c, 2, 2)) ||
                        // other diagonal
                        (cellContains(c, 0, 2) && cellContains(c, 1, 1) && cellContains(c, 2, 0))
        );
    }


    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getComputerPlayer() {
        return computerPlayer;
    }


}

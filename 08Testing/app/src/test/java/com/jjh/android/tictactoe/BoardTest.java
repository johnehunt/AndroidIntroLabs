package com.jjh.android.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;
    private Counter X = new Counter(Counter.X);
    private Counter Y = new Counter(Counter.Y);

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void addMoveAMoveToTheBoardAt00() {

        Move move = new Move(0, 0, X);
        board.addMove(move);
        assertFalse("cell should contain a counter", board.isCellEmpty(0, 0));
        assertTrue("cell should contain counter", board.cellContains(X, 0, 0));

    }

    @Test
    public void whenCreatedCellsAreAllEmpty() {
        assertTrue("cell should be empty at start", board.isCellEmpty(0, 0));
    }

    @Test
    public void whenCreatedBoardisNotFull() {
        assertFalse("board should not be full at start", board.isFull());
    }
}
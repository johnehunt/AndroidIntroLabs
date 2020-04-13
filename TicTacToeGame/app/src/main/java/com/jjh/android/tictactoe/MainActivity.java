package com.jjh.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private Button restartButton;

    class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getSimpleName(), "MainActivity.ButtonHandler.onClick()");
            Button buttonClicked = (Button)v;
            String buttonText = buttonClicked.getText().toString();
            if (!buttonText.equals(" ")) {
                Toast.makeText(MainActivity.this, "Cell is already in use!", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Player player = board.getHumanPlayer();
                boolean finished = checkGameStatus(player, buttonClicked);
                if (finished) {
                    restartButton.setEnabled(true);
                } else {
                    Button buttonSelected = makeComputerMove();
                    finished = checkGameStatus(board.getComputerPlayer(), buttonSelected);
                    if (finished) {
                        restartButton.setEnabled(true);
                    }
                }
            }

        }
    }

    class RestartButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getSimpleName(), "MainActivity.RestartButtonHandler.onClick()");
            setupNewBoard();
            Button button = findViewById(R.id.button0);
            button.setText(" ");
            button = findViewById(R.id.button1);
            button.setText(" ");
            button = findViewById(R.id.button2);
            button.setText(" ");
            button = findViewById(R.id.button3);
            button.setText(" ");
            button = findViewById(R.id.button4);
            button.setText(" ");
            button = findViewById(R.id.button5);
            button.setText(" ");
            button = findViewById(R.id.button6);
            button.setText(" ");
            button = findViewById(R.id.button7);
            button.setText(" ");
            button = findViewById(R.id.button8);
            button.setText(" ");
            restartButton.setEnabled(false);
        }
    }

    public MainActivity() {
        Log.d(this.getClass().getSimpleName(), "constructor()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonHandler handler = new ButtonHandler();

        // Setup buttons - all buttons use the same handler
        Button button = findViewById(R.id.button0);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button1);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button2);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button3);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button4);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button5);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button6);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button7);
        button.setOnClickListener(handler);
        button = findViewById(R.id.button8);
        button.setOnClickListener(handler);

        // Disable restart
        restartButton = findViewById(R.id.button9);
        restartButton.setOnClickListener(new RestartButtonHandler());
        restartButton.setEnabled(false);

        // Make first move
        setupNewBoard();
    }

    private void setupNewBoard() {
        Log.d(this.getClass().getSimpleName(), "setupNewBoard()");
        this.board = new Board();
        if (board.getFirstPlayer().isAutomatedPlayer()) {
            makeComputerMove();
        }
    }

    private Button makeComputerMove() {
        Log.d(this.getClass().getSimpleName(), "makeComputerMove()");
        ComputerPlayer cp = (ComputerPlayer)board.getComputerPlayer();
        Move move = cp.getMove();
        board.addMove(move);
        String tag = move.getX() + "," + move.getY();
        LinearLayout layout = this.findViewById(R.id.mainLayout);
        Button buttonSelected = layout.findViewWithTag(tag);
        buttonSelected.setText(move.getCounter().toString());
        return buttonSelected;
    }

    private boolean checkGameStatus(Player player, Button buttonClicked) {
        Log.d(this.getClass().getSimpleName(), "checkGameStatus()");
        Counter counter = player.getCounter();
        Move move = new Move(getButtonRow(buttonClicked), getButtonCol(buttonClicked), counter);
        board.addMove(move);
        buttonClicked.setText(counter.toString());
        boolean finished = false;
        if (board.checkForWinner(player)) {
            showWinnerMessage(player);
            finished = true;
        }
        if (!finished && board.isFull()) {
            showTieMessage(player);
            finished = true;
        }
        return finished;
    }

    private void showWinnerMessage(Player player) {
        Log.d(this.getClass().getSimpleName(), "showWinnerMessage()");
        Toast.makeText(MainActivity.this, "Well Done " + player + " WON!!", Toast.LENGTH_LONG)
                .show();
    }

    private void showTieMessage(Player player) {
        Log.d(this.getClass().getSimpleName(), "showWinnerMessage()");
        Toast.makeText(MainActivity.this, "The Game was a Tie!!", Toast.LENGTH_LONG)
                .show();
    }

    private int getButtonRow(Button button) {
        String tagString = (String) button.getTag();
        String rowString = tagString.substring(0,1);
        return Integer.parseInt(rowString);
    }

    private int getButtonCol(Button button) {
        String tagString = (String) button.getTag();
        String colString = tagString.substring(2,3);
        return Integer.parseInt(colString);
    }
}

package com.jjh.android.games;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static int MAX_NUMBER = 10;

    private EditText userGuessTextField;
    private int numberToGuess;

    public MainActivity() {
        numberToGuess = generateRandomNumber();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userGuessTextField = findViewById(R.id.guessEditText);
        userGuessTextField.setText("");
        TextView title = findViewById(R.id.title);
        title.setText(title.getText().toString() + MAX_NUMBER);
    }

    public void onGuessSubmit(View view) {
        String userInput = userGuessTextField.getText().toString();
        validateAndCheckGuess(userInput);
    }

    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(MAX_NUMBER -1) + 1;
    }

    private void validateAndCheckGuess(String userInput) {
        try {
            int guessInput = Integer.parseInt(userInput);
            if (guessInput < 1 || guessInput > MAX_NUMBER) {
                displayInvalidUserInputToast();
            } else {
                checkForCorrectGuess(guessInput);
            }
        } catch (NumberFormatException e) {
            displayInvalidUserInputToast();
        }
    }

    private void checkForCorrectGuess(int guessInput) {
        if (guessInput == numberToGuess) {
            displayCorrectAnswerToast();
            userGuessTextField.setText("");
            numberToGuess = generateRandomNumber();
        } else {
            displayHintToast(guessInput > numberToGuess);
        }
    }

    private void displayCorrectAnswerToast() {
        Toast.makeText(this, "You guessed correctly! The number was " + numberToGuess, Toast.LENGTH_LONG).show();
    }

    private void displayInvalidUserInputToast() {
        Toast.makeText(this, "Invalid input (input must be between 1 and " + MAX_NUMBER + ")", Toast.LENGTH_SHORT).show();
    }

    private void displayHintToast(Boolean higher) {
        String message = "The correct answer is higher";
        if (higher) {
            message = "The correct answer is lower";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

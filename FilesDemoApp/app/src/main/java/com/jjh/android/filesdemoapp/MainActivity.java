package com.jjh.android.filesdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    class SaveButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            saveText();
        }
    }

    class LoadButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            loadText();
        }
    }

    class ClearButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            clearText();
        }
    }

    private static final String FILENAME = "datafile.txt";
    private EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(new SaveButtonHandler());

        button = findViewById(R.id.loadButton);
        button.setOnClickListener(new LoadButtonHandler());

        button = findViewById(R.id.clearButton);
        button.setOnClickListener(new ClearButtonHandler());

        textView = findViewById(R.id.editText);

    }

    private void saveText() {
        CharSequence data = textView.getText();

        Charset charset = Charset.forName("UTF-8"); ;
        try (FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE)) {
            fos.write(data.toString().getBytes(charset));
        } catch (IOException exp) {
            showErrorMessage(exp.getMessage());
        }
    }

    private void loadText() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
            } catch (IOException exp) {
                showErrorMessage(exp.getMessage());
            } finally {
                String contents = stringBuilder.toString();
                textView.setText(contents);
            }
        } catch (IOException exp) {
            showErrorMessage(exp.getMessage());
        }
    }

    private void clearText() {
        textView.setText("");
    }

    private void showErrorMessage(String msg) {
        Toast.makeText(this, "Error: " + msg, Toast.LENGTH_LONG).show();
    }
}

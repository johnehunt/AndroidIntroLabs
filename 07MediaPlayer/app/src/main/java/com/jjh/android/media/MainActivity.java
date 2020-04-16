package com.jjh.android.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int forwardTime = 5000;
    private static final int backwardTime = 5000;

    private Button fastForward, pause, play, rewind;
    private MediaPlayer mediaPlayer;

    private boolean firstTime = true;
    private double startTime = 0;
    private double finalTime = 0;
    private SeekBar seekbar;

    private final Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup buttons
        fastForward = findViewById(R.id.forward);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        rewind = findViewById(R.id.rewind);

        // Setup Handlers
        fastForward.setOnClickListener(new ForwardHandler());
        pause.setOnClickListener(new PauseHandler());
        play.setOnClickListener(new PlayHandler());
        rewind.setOnClickListener(new RewindHandler());

        // Determine which buttons are enabled at start
        pause.setEnabled(false);

        // Setup Media Player - note must manually add raw folder
        // under the res folder on your project
        // place song.mp3 file in here
        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        // Setup seekbar
        seekbar = findViewById(R.id.seekBar);
        seekbar.setClickable(false);

    }

    class PlayHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();

            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();

            // Set up seekbar
            if (firstTime) {
                seekbar.setMax((int) finalTime);
                firstTime = false;
            }

            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(updateSeekBarRunnable, 100);

            // Enable / Disable appropriate buttons
            pause.setEnabled(true);
            play.setEnabled(false);

        }
    }

    class PauseHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_SHORT).show();
            mediaPlayer.pause();
            pause.setEnabled(false);
            play.setEnabled(true);
        }

    }

    class ForwardHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            int temp = mediaPlayer.getCurrentPosition();

            if ((temp + forwardTime) <= finalTime) {
                startTime = temp + forwardTime;
                mediaPlayer.seekTo((int) startTime);
                Toast.makeText(getApplicationContext(), "Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
            }
        }

    }

    class RewindHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int temp = mediaPlayer.getCurrentPosition();

            if ((temp - backwardTime) > 0) {
                startTime = temp - backwardTime;
                mediaPlayer.seekTo((int) startTime);
                Toast.makeText(getApplicationContext(), "Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private final Runnable updateSeekBarRunnable = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}


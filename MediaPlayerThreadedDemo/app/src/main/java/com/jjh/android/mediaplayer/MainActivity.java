package com.jjh.android.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PlayerService service;
    private Button pause, play, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup button references
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);

        // Determine which buttons are enabled at start
        pause.setEnabled(false);
        stop.setEnabled(false);

        // Create intent to bind to service
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, new ServiceConnectionHandler(), Context.BIND_AUTO_CREATE);

    }

    // Button Handler methods

    public void onPlayButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
        try {
            service.start();
        } catch (Exception exp) {
            Toast.makeText(getApplicationContext(),
                    "Problem starting source " + exp.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        // Enable / Disable appropriate buttons
        pause.setEnabled(true);
        play.setEnabled(false);
        stop.setEnabled(true);
    }

    public void onPauseButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_SHORT).show();
        service.pause();

        // Enable / Disable buttons
        pause.setEnabled(false);
        play.setEnabled(true);
        stop.setEnabled(false);
    }

    public void onStopButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "Stopping sound", Toast.LENGTH_SHORT).show();
        service.stop();
        try {
            // Need to prepare mediaPlayer
            // so that it can play another tune
            service.prepare();
        } catch (Exception exp) {
            Toast.makeText(getApplicationContext(),
                    "Problem playing source " + exp.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        // Enable / Disable buttons
        pause.setEnabled(false);
        play.setEnabled(true);
        stop.setEnabled(false);
    }

    // Service Connection Listener

    private class ServiceConnectionHandler implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName className, IBinder binder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            PlayerService.DemoBinder demoBinder = (PlayerService.DemoBinder) binder;
            service = demoBinder.getService();
            Log.d("ServiceConnection", "service bound");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
}

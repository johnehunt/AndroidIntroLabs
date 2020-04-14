package com.jjh.android.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public MainActivity() {
        Log.d(TAG,"constructor()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        setContentView(R.layout.activity_main);
        Log.d(TAG,"setContentView()");
        if (savedInstanceState != null) {
            String msg = savedInstanceState.getString("msg");
            Log.d(TAG, "onCreate() - " + msg);
        }
    }

    // Restoring / Saving Dynamic State
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState()");
        String msg = savedInstanceState.getString("msg");
        Log.d(TAG,"onRestoreInstanceState() - " + msg);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("msg", "Hello Message " + new Date());
        Log.d(TAG,"onSaveInstanceState()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}

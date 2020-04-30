package com.jjh.android.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerService extends Service {

    private static final String TAG = "PlayerService";

    private IBinder binder = new DemoBinder();
    MediaPlayer mediaPlayer;
    ExecutorService executor;

    public PlayerService() {
        // ensures a single thread is reused and runnables
        // executed in sequence submitted
        executor = Executors.newSingleThreadExecutor();
    }

    // Service Functionality - each runs in a background thread
    public void start() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        });
        executor.execute(t);
    }

    public void pause() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.pause();
            }
        });
        executor.execute(t);
    }

    public void stop() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        });
        executor.execute(t);
    }

    public void prepare() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Need to prepare mediaPlayer
                    // so that it can play another tune
                    mediaPlayer.prepare();
                } catch (Exception exp) {
                    Log.d("PlayerService", "MediaPlayer problem", exp);
                }
            }
        });
        executor.execute(t);
    }

    public class DemoBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    // Lifecycle Methods

    @Override
    public void onCreate() {
        super.onCreate();
        PlayerHandlerThread t = new PlayerHandlerThread();
        t.start();
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return binder;
    }

    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return true;
    }

    public void onDestroy() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        } catch (Exception exp) {
            Toast.makeText(getApplicationContext(),
                    "Problem starting source " + exp.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        super.onDestroy();
    }

    // Handler Thread class
    // Need to run in a HandlerThread for call backs to run in this thread
    // Handler threads gave a looper which is required to ensure callback
    // methods don;t run on the UI thread.
    class PlayerHandlerThread extends HandlerThread {

        public PlayerHandlerThread() {
            super("PlayerHandlerThread");
        }

        public void onLooperPrepared() {
            mediaPlayer = MediaPlayer.create(PlayerService.this, R.raw.song);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    Log.e("onLooperPrepared", mp.toString());
                }
            });
        }

    }

}

package com.jjh.android.intentserviceresultdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The purpose of this demonstration application
 * is to illustrate how an IntentService can return
 * a result back to a triggering activity.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ResultReceiver receiver;
    private EditText input;
    TextView result;

    // Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()");

        // Set up refs to views
        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

        // Set up receiver
        receiver = new FactorialResultReceiver(new Handler());
    }

    // Button handler methods
    public void onClickStartService(View v) {
        Log.d(TAG, "onClickStartService()");
        int numberToCalculate = getInputAsInt();
        // Create intent to launch service
        Intent intent = new Intent(this, FactorialCalculationService.class);
        // Register the receiver
        intent.putExtra(FactorialCalculationService.RECEIVER, receiver);
        // Add data to be used by service
        intent.putExtra(FactorialCalculationService.NUMBER, numberToCalculate);
        // Start the service
        startService(intent);
    }

    private int getInputAsInt() {
        Log.d(TAG, "getInputAsInt()");
        String inputString = input.getText().toString();
        return Integer.parseInt(inputString);
    }

    // Inner class to handle result

    private class FactorialResultReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public FactorialResultReceiver(Handler handler) {
            super(handler);
            Log.d(TAG, "FactorialResultReceiver.<init>()");
        }

        // Will be called when the Service returns a result
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            Log.d(TAG, "FactorialResultReceiver.onReceiveResult()");
            switch (resultCode) {
                case FactorialCalculationService.SUCCESS:
                    int calculatedTotal = resultData.getInt("result");
                    // update the display
                    result.setText(Integer.toString(calculatedTotal));
                    break;
                case FactorialCalculationService.ERROR:
                    Toast.makeText(getApplicationContext(), "Error in Calculation",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            super.onReceiveResult(resultCode, resultData);
        }

    }
}

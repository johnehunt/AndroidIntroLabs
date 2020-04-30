package com.jjh.android.intentserviceresultdemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

public class FactorialCalculationService extends IntentService {

    private static final String TAG = "FactorialCalculationSer";

    // Set up some constants for Activity to use
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final String NUMBER = "number";
    public static final String RESULT = "result";
    public static final String RECEIVER = "receiver";

    public FactorialCalculationService() {
        super(FactorialCalculationService.class.getSimpleName());
        Log.d(TAG, "<init>()");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberToCalculate = intent.getIntExtra(NUMBER, 0);
        int result = factorial(numberToCalculate);
        // Now need to return result from the service
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        Bundle bundle = new Bundle();
        bundle.putInt(RESULT, result);
        receiver.send(SUCCESS, bundle);
    }

    private int factorial(int num) {
        Log.d(TAG, "factorial(" + num + ")");
        int result = 1;
        if (num > 0) {
            result = 1;
            for (int i = 1; i <= num; i++) {
                result = result * i;
            }
        }
        return result;
    }
}

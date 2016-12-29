package com.teambition.sample.disposablemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.teambition.sample.disposablemanager.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startNew).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnDispose(() -> Log.i(TAG, "Unsubscribing subscription from onCreate()"))
            .subscribe(num -> {
                Log.i(TAG, "Started in onCreate(), running until destory(): " + num);
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnDispose(() -> Log.i(TAG, "Unsubscribing subscription from onStart()"))
            .subscribe(num -> {
                Log.i(TAG, "Started in onStart(), running until onDestroy(): " + num);
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle", "onresume");
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnDispose(() -> Log.i(TAG, "Unsubscribing subscription from onResume()"))
            .subscribe(num -> {
                Log.i(TAG, "Started in onResume(), running until onDestroy(): " + num);
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle", "ondestroy");
    }
}

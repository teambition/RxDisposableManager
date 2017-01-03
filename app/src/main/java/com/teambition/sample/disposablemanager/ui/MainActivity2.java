package com.teambition.sample.disposablemanager.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.teambition.sample.disposablemanager.R;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Copyright ©2016 by Teambition
 */
public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = MainActivity2.class.getSimpleName();
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnDispose(() -> Log.i(TAG, "Unsubscribing subscription from onCreate()"))
            .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(num -> {
                Log.i(TAG, "Started in onCreate(), running until destory(): " + num);
            });
    }


    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}

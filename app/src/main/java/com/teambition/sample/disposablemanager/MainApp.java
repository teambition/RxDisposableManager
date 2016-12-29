package com.teambition.sample.disposablemanager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.teambition.sample.disposablemanager.lifecycle.ActivityStack;
import com.teambition.sample.disposablemanager.lifecycle.DisposableManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Copyright ©2016 by Teambition
 */
public class MainApp extends Application {
    public static final String TAG = MainApp.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e(TAG,"main create");
        super.onCreate();
        // key method : can get all disposable(equal RxJava1.Subscription) by this method
        RxJavaPlugins.setOnObservableSubscribe((observable, observer) -> {
            if (observer instanceof LambdaObserver) {
                Log.e(TAG, "catch observer" + observer.hashCode());
                DisposableManager.getInstance().add(ActivityStack.getTop(), (Disposable) observer);
            }
            return observer;
        });
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ActivityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStack.remove(activity);
                DisposableManager.getInstance().remove(activity);
            }
        });
    }
}

package com.teambition.sample.disposablemanager.lifecycle;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.disposables.Disposable;

/**
 * Copyright ©2016 by Teambition
 */
public final class DisposableManager implements LifecycleManager<Activity, Disposable> {
    private Map<Activity, List<Disposable>> container;

    private DisposableManager() {
        container = new WeakHashMap<>();
    }

    private static DisposableManager INSTANCE = new DisposableManager();

    public static DisposableManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Activity activity, Disposable disposable) {
        if (activity == null) {
            Log.e("error", "activity stack has no item");
            return;
        }
        if (!container.containsKey(activity)) {
            container.put(activity, new ArrayList<>());
        }
        container.get(activity).add(disposable);

    }

    @Override
    public void remove(Activity activity) {
        if (container.containsKey(activity)) {
            for (Disposable disposable : container.get(activity)) {
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
            container.remove(activity);
        }
    }
}

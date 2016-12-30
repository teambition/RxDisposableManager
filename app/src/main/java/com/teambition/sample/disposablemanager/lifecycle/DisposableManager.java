package com.teambition.sample.disposablemanager.lifecycle;

import android.app.Activity;
import android.util.Log;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Copyright ©2016 by Teambition
 */
public final class DisposableManager implements LifecycleManager<Disposable> {
    private Map<Activity, CompositeDisposable> container;

    private DisposableManager() {
        container = new WeakHashMap<>();
    }

    private static DisposableManager INSTANCE = new DisposableManager();

    public static DisposableManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Disposable disposable) {
        Activity activity = ActivityStack.getTop();
        if (activity == null) {
            Log.e("error", "activity stack has no item");
            return;
        }
        if (!container.containsKey(activity)) {
            container.put(activity, new CompositeDisposable());
        }
        container.get(activity).add(disposable);
    }

    @Override
    public void remove(Disposable disposable) {
        for (CompositeDisposable disposables : container.values()) {
            disposables.remove(disposable);
        }
    }

    public void escape(Disposable disposable) {
        for (CompositeDisposable disposables : container.values()) {
            disposables.delete(disposable);
        }
    }

    @Override
    public void clear() {

    }

    public void clear(Activity activity) {
        if (activity != null && container.containsKey(activity)) {
            container.get(activity).dispose();
        }
    }
}

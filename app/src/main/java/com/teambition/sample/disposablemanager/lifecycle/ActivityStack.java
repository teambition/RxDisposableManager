package com.teambition.sample.disposablemanager.lifecycle;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright ©2016 by Teambition
 */
public class ActivityStack {
    private static List<WeakReference<Activity>> list = new ArrayList<>();

    public static void add(Activity activity) {
        list.add(new WeakReference<>(activity));
    }

    public static void remove(Activity activity) {
        list.remove(activity);
    }

    public static Activity getTop() {
        Activity activity = list.get(list.size() - 1).get();
        if (activity == null) {
            return null;
        } else {
            if (activity.isFinishing() && list.size() >= 2) {
                return list.get(list.size() - 2).get();
            }
            return activity;
        }
    }
}

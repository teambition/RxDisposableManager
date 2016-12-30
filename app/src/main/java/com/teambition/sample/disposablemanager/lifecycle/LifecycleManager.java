package com.teambition.sample.disposablemanager.lifecycle;

/**
 * Copyright ©2016 by Teambition
 */
public interface LifecycleManager<T> {
    abstract void add(T t);

    abstract void remove(T t);

    abstract void escape(T t);

    abstract void clear();
}

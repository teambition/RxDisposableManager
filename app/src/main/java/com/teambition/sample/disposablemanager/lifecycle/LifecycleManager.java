package com.teambition.sample.disposablemanager.lifecycle;

/**
 * Copyright ©2016 by Teambition
 */
public interface LifecycleManager<T, E> {
    void add(T t, E e);

    void remove(T t);
}

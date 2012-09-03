package com.ad.api;

public interface Cache<T> {
    T getDelegate();
    void set(String key, Integer timeOut, Object value);
    void delete(String key);
    <Value> Value get(String key, Class<Value> vClass);
    <Value> Value aSyncGet(String key, Class<Value> valueClass, long duration);

    public static interface CacheElement
    {
        String geKey();
    }
}

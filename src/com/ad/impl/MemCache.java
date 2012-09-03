package com.ad.impl;

import com.ad.api.Cache;
import com.ad.api.Constants;
import com.ad.e.MemCacheException;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.GetFuture;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class MemCache implements Cache<MemcachedClient> {
    private final String address;

    private MemCache(String address) {
        this.address = address;
    }

    public static MemCache getInstance() {
        String memCacheAddress = System.getProperty(Constants.MEM_CACHE_ADDRESS_KEY);
        if (memCacheAddress == null) memCacheAddress = Constants.MEM_CACHE_DEFAULT_ADDRESS;
        return new MemCache(memCacheAddress);
    }

    public MemcachedClient getDelegate() {
        try {
            return new MemcachedClient(AddrUtil.getAddresses(address));
        } catch (IOException e) {
            throw new MemCacheException(e);
        }
    }

    @Override
    public void set(String key, Integer timeLive, Object value) {
        getDelegate().set(key, timeLive, value);
    }

    @Override
    public <V> V aSyncGet(String key, Class<V> valueClass, long duration) {
        GetFuture<Object> objectGetFuture = getDelegate().asyncGet(key);
        try {
            Object value = objectGetFuture.get(duration, TimeUnit.MICROSECONDS);
            return valueClass.cast(value);
        } catch (Exception e) {
            objectGetFuture.cancel(true);
            throw new MemCacheException(e);
        }
    }

    @Override
    public <V> V get(String key, Class<V> vClass) {
        return vClass.cast(getDelegate().get(key));
    }

    @Override
    public void delete(String key) {
        getDelegate().delete(key);
    }
}

package com.ad.api;

import com.ad.e.AdServiceTimeOutException;
import com.ad.impl.MemCache;

public class AdService {
    protected final JDBCProvider jdbcProvider = JDBCProvider.POSTGRESQL_DRIVER;
    protected final MemCache memCache = MemCache.getInstance();
    private CacheMode cacheMode = CacheMode.ON;

    protected final <TResponse extends JDBCProvider.DBElement<TRequest>, TRequest extends Cache.CacheElement> TResponse processRequest(TRequest request, Class<TResponse> vClass) {
        TResponse response;
        if (CacheMode.ON.equals(cacheMode)) {
            response = fromCache(request, vClass);
        }
        else
        {
            response = fromDB(request, vClass);
        }
        return response;
    }

    protected void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }

    protected final <TResponse extends JDBCProvider.DBElement<TRequest>, TRequest extends Cache.CacheElement> TResponse fromDB(TRequest request, Class<TResponse> vClass) {
        try {
            return vClass.getConstructor(JDBCProvider.class, request.getClass()).newInstance(jdbcProvider, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final <TResponse, TRequest extends Cache.CacheElement> TResponse fromCache(TRequest request, Class<TResponse> vClass) {
        String key = request.geKey();
        TResponse response = memCache.get(key, vClass);
        if (response == null) {
            memCache.set(key, Constants.EXP, request);
            response = memCache.aSyncGet(key, vClass, Constants.DURATION);
            if (response == null) {
                throw new AdServiceTimeOutException(key, Constants.DURATION);
            }
        }
        return response;
    }

    protected static enum CacheMode {
        OFF,
        ON
    }
}

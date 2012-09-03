package com.ad.impl;

import com.ad.api.Builder;
import com.ad.api.Cache;
import com.ad.api.Constants;
import com.ad.api.JDBCProvider;
import net.spy.memcached.MemcachedNode;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class ResponseBuilder<TRequest extends Cache.CacheElement, TResponse extends JDBCProvider.DBElement<TRequest>> implements Builder<TResponse>{
    private MemCache memCache;
    private JDBCProvider provider;
    private final String requestKey;

    public Class<TRequest> requestClass;
    public Class<TResponse> responseClass;

    public ResponseBuilder(String requestKey) {
        this.requestKey = requestKey;
    }

    public void setMemCache(MemCache memCache) {
        this.memCache = memCache;
    }

    public void setProvider(JDBCProvider provider) {
        this.provider = provider;
    }

    public ResponseBuilder setRequestClass(Class<TRequest> requestClass) {
        this.requestClass = requestClass;
        return this;
    }

    public ResponseBuilder setResponseClass(Class<TResponse> responseClass) {
        this.responseClass = responseClass;
        return this;
    }

    @Override
    public TResponse build() {
        TRequest request = memCache.get(requestKey, requestClass);

        try {
            Constructor<TResponse> constructor = responseClass.getConstructor(JDBCProvider.class, requestClass);
            return constructor.newInstance(provider, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static final class Daemon implements Runnable
    {
        private final MemCache memCache = MemCache.getInstance();
        private final JDBCProvider provider = JDBCProvider.POSTGRESQL_DRIVER;
        private final Set<String> dbKeys = new HashSet<String>();

        public Daemon() {
            provider.openConnection();
        }

        private Set<String> getPackageKeys()
        {
            //todo: Need to fill
            return new HashSet<String>();
        }

        private Set<String> getReportKeys()
        {
            //todo: Need to fill
            return new HashSet<String>();
        }

        @Override
        public void run() {
            while (true)
            {
                processReportKeys();
                processPackageKeys();
                sleep();
            }
        }

        private void sleep() {
            try {
                Thread.sleep(Constants.DAEMON_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void processReportKeys() {
            int i = 0;
            for (String dbKey: getReportKeys())
            {
                if (i > Constants.DAEMON_KEY_LIMIT) break;
                String memKey = generateMemKey(dbKey, ReportRequest.class);
                ReportRequest reportRequest = memCache.get(memKey, ReportRequest.class);
                if (reportRequest != null)
                {
                    ResponseBuilder<ReportRequest, ReportResponse> builder = new ResponseBuilder<ReportRequest, ReportResponse>(memKey);
                    builder.setMemCache(memCache);
                    builder.setProvider(provider);
                    builder.setRequestClass(ReportRequest.class);
                    builder.setResponseClass(ReportResponse.class);
                    ReportResponse reportResponse = builder.build();
                    memCache.set(memKey, Constants.EXP, reportResponse);
                }
                i++;
            }
        }

        private void processPackageKeys() {
            int i = 0;
            for (String dbKey: getPackageKeys())
            {
                if (i > Constants.DAEMON_KEY_LIMIT) break;
                String memKey = generateMemKey(dbKey, ADRequest.class);
                ADRequest adRequest = memCache.get(memKey, ADRequest.class);
                if (adRequest != null)
                {
                    ResponseBuilder<ADRequest, ADResponse> builder = new ResponseBuilder<ADRequest, ADResponse>(memKey);
                    builder.setMemCache(memCache);
                    builder.setProvider(provider);
                    builder.setRequestClass(ADRequest.class);
                    builder.setResponseClass(ADResponse.class);
                    ADResponse adResponse = builder.build();
                    memCache.set(memKey, Constants.EXP, adResponse);
                }

                i++;
            }
        }

        public static String generateMemKey(String dbKey, Class clazz)
        {
            return clazz.getSimpleName() + Constants.CLASS_SEPARATOR + dbKey;
        }
    }
}

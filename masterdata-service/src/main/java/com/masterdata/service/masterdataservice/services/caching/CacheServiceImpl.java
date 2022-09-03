package com.masterdata.service.masterdataservice.services.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private CacheManager cacheManager;


    @Override
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Override
    public void evictCacheByName(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }

    @Scheduled(fixedDelayString = "${app.cacheRefreshTime}", initialDelay = 1000)
    public void evictAllCachesAtIntervals(){
        evictAllCaches();
    }
}

package com.masterdata.service.masterdataservice.services.caching;

public interface CacheService {
    void evictAllCaches();
    void evictCacheByName(String cacheName);
}

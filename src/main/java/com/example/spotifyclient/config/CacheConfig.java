package com.example.spotifyclient.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager tokenCacheManager = new CaffeineCacheManager("spotifyToken");
        tokenCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(55, TimeUnit.MINUTES));

        CaffeineCacheManager albumCacheManager = new CaffeineCacheManager("spotifyAlbums");
        albumCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS));

        return new CompositeCacheManager(tokenCacheManager, albumCacheManager);
    }
}

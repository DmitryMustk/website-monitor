package com.example.demo.comparator;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class CachingContentComparator implements ContentComparator {
    private static final String CACHE_NAME = "contentChanges";

    private final ContentComparator delegate;
    private final CacheManager cacheManager;

    @Override
    public boolean isContentChanged(String previousContent, String currentContent) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        if (cache == null) {
            throw new CacheNotFoundException("Cache \"%s\" not found".formatted(CACHE_NAME));
        }
        String cacheKey = generateCacheKey(previousContent, currentContent);

        return Boolean.TRUE.equals(cache.get(
                cacheKey,
                () -> delegate.isContentChanged(previousContent, currentContent)
        ));
    }

    private String generateCacheKey(String str1, String str2) {
        return str1.hashCode() + "_" + str2.hashCode();
    }
}

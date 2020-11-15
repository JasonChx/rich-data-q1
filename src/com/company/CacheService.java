package com.company;

import com.sun.istack.internal.NotNull;

public interface CacheService {

    /**
     * @param key
     * @param value
     * @param ttl time to live
     * @return boolean
     */
    public boolean setCache(@NotNull String key, @NotNull String value, int ttl);

    /**
     * @param key
     * @return cache value
     */
    public String getCacheValueByKey(@NotNull String key);
}

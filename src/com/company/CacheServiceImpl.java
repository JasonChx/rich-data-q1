package com.company;

import com.sun.istack.internal.NotNull;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService{

    protected static final Log log = LogFactory.getLog(CacheServiceImpl.class);

    private final static String CACHE_NAME="MyCache";

    @Override
    public boolean setCache(@NotNull String key, @NotNull String value, int ttl) {
        //get cache
        Cache cache = getCache();

        //remove existing
        cache.remove(key);

        //create element
        Element element = new Element(key, value);
        element.setTimeToLive(ttl);

        //Store an element
        cache.put(element);
        return true;
    }

    @Override
    public String getCacheValueByKey(@NotNull String key){
        //get cache
        Cache cache = getCache();

        //Retrieve an element from cache
        Element el = cache.get(key);
        return el == null ? "Cannot find value for the key: " + key : (String) el.getObjectValue();
    }

    private Cache getCache(){
        CacheManager cacheMgr = CacheManager.newInstance();

        //Initialise a cache if it does not already exist
        if (cacheMgr.getCache(CACHE_NAME) == null) {
            cacheMgr.addCache(CACHE_NAME);
        }

        return cacheMgr.getCache(CACHE_NAME);
    }
}

package net.easipay.dsfc.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheManager
{
    public static final CacheManager instance = new CacheManager();

    private static Map<String, CacheNode> cacheMap = new ConcurrentHashMap<String, CacheNode>();

    private CacheNode getCacheNode(String type)
    {
	return (CacheNode) cacheMap.get(type);
    }

    private boolean hasCache(String type)
    {
	return cacheMap.containsKey(type);
    }

    protected CacheNode getCacheInfo(String type)
    {
	if (hasCache(type)) {
	    CacheNode cache = getCacheNode(type);
	    if (cacheExpired(cache)) {
		cache.setExpired(true);
	    }
	    return cache;
	}
	else {
	    return null;
	}
    }

    protected boolean cacheExpired(CacheNode cache)
    {
	if (null == cache) {
	    return false;
	}
	long nowDt = System.currentTimeMillis();
	long cacheDt = cache.getTimeOut();
	if (cacheDt <= 0 || cacheDt > nowDt) {
	    return false;
	}
	else {
	    return true;
	}
    }

    protected void putCacheNode(String type, CacheNode cacheNode)
    {
	cacheMap.put(type, cacheNode);
    }

    public static Object getCache(String type)
    {
	return getCache(type, false);
    }

    public static Object getCache(String type, boolean refresh)
    {
	CacheNode cacheNode = CacheManager.instance.getCacheInfo(type);
	if (refresh || cacheNode == null || cacheNode.isExpired()) {
	    try {
		cacheNode = CacheExecuter.execute(cacheNode);
	    } catch ( Exception e ) {
		CacheLogger.error(String.format("[ type - %s ] Failed to refresh cache", type), e);
	    }
	}
	return cacheNode == null ? null : cacheNode.getValue();
    }

}
